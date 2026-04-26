package edu.only4.danmuku.adapter.application.queries.video_hls_encrypt_key

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.adapter.domain.repositories.VideoHlsEncryptKeyRepository
import edu.only4.danmuku.application.queries.video_hls_encrypt_key.UniqueVideoHlsEncryptKeyVideoPostIdFileIndexKeyIdKeyVersionQualityDeletedQry
import edu.only4.danmuku.domain._share.meta.video_hls_encrypt_key.SVideoHlsEncryptKey
import org.springframework.stereotype.Service

@Service
class UniqueVideoHlsEncryptKeyVideoPostIdFileIndexKeyIdKeyVersionQualityDeletedQryHandler(
    private val repository: VideoHlsEncryptKeyRepository,
) : Query<UniqueVideoHlsEncryptKeyVideoPostIdFileIndexKeyIdKeyVersionQualityDeletedQry.Request, UniqueVideoHlsEncryptKeyVideoPostIdFileIndexKeyIdKeyVersionQualityDeletedQry.Response> {
    override fun exec(request: UniqueVideoHlsEncryptKeyVideoPostIdFileIndexKeyIdKeyVersionQualityDeletedQry.Request): UniqueVideoHlsEncryptKeyVideoPostIdFileIndexKeyIdKeyVersionQualityDeletedQry.Response {
        val exists = repository.exists(
            SVideoHlsEncryptKey.specify { schema ->
                schema.all(
                    schema.videoPostId eq request.videoPostId,
                    schema.fileIndex eq request.fileIndex,
                    schema.keyId eq request.keyId,
                    schema.keyVersion eq request.keyVersion,
                    schema.quality eq request.quality,
                    schema.deleted eq request.deleted,
                    schema.id `neq?` request.excludeVideoHlsEncryptKeyId ?: schema.id.isNotNull(),
                )
            }
        )

        return UniqueVideoHlsEncryptKeyVideoPostIdFileIndexKeyIdKeyVersionQualityDeletedQry.Response(
            exists = exists
        )
    }
}
