package edu.only4.danmuku.adapter.application.queries.video_hls_key_token

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.adapter.domain.repositories.VideoHlsKeyTokenRepository
import edu.only4.danmuku.application.queries.video_hls_key_token.UniqueVideoHlsKeyTokenTokenHashDeletedQry
import edu.only4.danmuku.domain._share.meta.video_hls_key_token.SVideoHlsKeyToken
import org.springframework.stereotype.Service

@Service
class UniqueVideoHlsKeyTokenTokenHashDeletedQryHandler(
    private val repository: VideoHlsKeyTokenRepository,
) : Query<UniqueVideoHlsKeyTokenTokenHashDeletedQry.Request, UniqueVideoHlsKeyTokenTokenHashDeletedQry.Response> {
    override fun exec(request: UniqueVideoHlsKeyTokenTokenHashDeletedQry.Request): UniqueVideoHlsKeyTokenTokenHashDeletedQry.Response {
        val exists = repository.exists(
            SVideoHlsKeyToken.specify { schema ->
                schema.all(
                    schema.tokenHash eq request.tokenHash,
                    schema.deleted eq request.deleted,
                    schema.id `neq?` request.excludeVideoHlsKeyTokenId ?: schema.id.isNotNull(),
                )
            }
        )

        return UniqueVideoHlsKeyTokenTokenHashDeletedQry.Response(
            exists = exists
        )
    }
}
