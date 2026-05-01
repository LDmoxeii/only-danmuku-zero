package edu.only4.danmuku.adapter.application.queries.video_encrypt

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.VideoHlsEncryptKey
import edu.only4.danmuku.application.queries._share.model.fileIndex
import edu.only4.danmuku.application.queries._share.model.id
import edu.only4.danmuku.application.queries._share.model.videoPostId
import edu.only4.danmuku.application.queries.video_encrypt.ListVideoHlsEncryptKeysByPostFileQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

/**
 * 按 videoPostId + fileIndex 查询密钥ID列表
 */
@Service
class ListVideoHlsEncryptKeysByPostFileQryHandler(
    private val sqlClient: KSqlClient,
) : Query<ListVideoHlsEncryptKeysByPostFileQry.Request, ListVideoHlsEncryptKeysByPostFileQry.Response> {

    override fun exec(request: ListVideoHlsEncryptKeysByPostFileQry.Request): ListVideoHlsEncryptKeysByPostFileQry.Response {
        val ids = sqlClient.createQuery(VideoHlsEncryptKey::class) {
            where(table.videoPostId eq request.videoPostId)
            where(table.fileIndex eq request.fileIndex)
            select(table.id)
        }.execute()

        return ListVideoHlsEncryptKeysByPostFileQry.Response(
            items = ids.map {
                ListVideoHlsEncryptKeysByPostFileQry.Response.EncryptKeyItem(
                    encryptKeyId = it
                )
            }
        )
    }
}
