package edu.only4.danmuku.adapter.application.queries.video_encrypt

import java.util.UUID

import com.only.engine.json.misc.JsonUtils
import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.VideoHlsEncryptKey
import edu.only4.danmuku.application.queries._share.model.fileIndex
import edu.only4.danmuku.application.queries._share.model.keyVersion
import edu.only4.danmuku.application.queries._share.model.quality
import edu.only4.danmuku.application.queries._share.model.status
import edu.only4.danmuku.application.queries._share.model.videoPostId
import edu.only4.danmuku.application.queries.video_encrypt.ListVideoHlsEncryptKeysQry
import edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.enums.EncryptKeyStatus
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

/**
 * 按 videoPostId + fileIndex + keyVersion 查询质量 key 列表
 */
@Service
class ListVideoHlsEncryptKeysQryHandler(
    private val sqlClient: KSqlClient,
) : Query<ListVideoHlsEncryptKeysQry.Request, ListVideoHlsEncryptKeysQry.Response> {

    override fun exec(request: ListVideoHlsEncryptKeysQry.Request): ListVideoHlsEncryptKeysQry.Response {
        val keyVersion = request.keyVersion
            ?: resolveLatestKeyVersion(request.videoPostId, request.fileIndex)
            ?: return ListVideoHlsEncryptKeysQry.Response(keysJson = "[]")
        val keys = sqlClient.createQuery(VideoHlsEncryptKey::class) {
            where(table.videoPostId eq request.videoPostId)
            where(table.fileIndex eq request.fileIndex)
            where(table.keyVersion eq keyVersion)
            select(table)
        }.execute()
        val payloads = keys.sortedWith(
            compareByDescending<VideoHlsEncryptKey> { qualityScore(it.quality ?: "") }
                .thenBy { it.quality ?: "" }
        ).map {
            KeyItem(
                quality = it.quality,
                keyId = it.keyId,
                keyVersion = it.keyVersion,
                status = it.status.name,
                keyUriTemplate = it.keyUriTemplate
            )
        }

        return ListVideoHlsEncryptKeysQry.Response(
            keysJson = JsonUtils.toJsonString(payloads) ?: "[]"
        )
    }

    private fun resolveLatestKeyVersion(videoPostId: UUID, fileIndex: Int): Int? {
        val versions = sqlClient.createQuery(VideoHlsEncryptKey::class) {
            where(table.videoPostId eq videoPostId)
            where(table.fileIndex eq fileIndex)
            where(table.status eq EncryptKeyStatus.ACTIVE)
            select(table.keyVersion)
        }.execute()
        return versions.maxOrNull()
    }

    private fun qualityScore(quality: String): Int {
        val number = QUALITY_NUMBER_REGEX.find(quality)?.groupValues?.getOrNull(1)?.toIntOrNull()
        return number ?: Int.MIN_VALUE
    }

    data class KeyItem(
        val quality: String?,
        val keyId: String,
        val keyVersion: Int,
        val status: String,
        val keyUriTemplate: String
    )

    companion object {
        private val QUALITY_NUMBER_REGEX = Regex("(\\d+)")
    }
}

