package edu.only4.danmuku.adapter.application.queries.video_hls_key_token

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_hls_key_token.UniqueVideoHlsKeyTokenQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class UniqueVideoHlsKeyTokenQryHandler : Query<UniqueVideoHlsKeyTokenQry.Request, UniqueVideoHlsKeyTokenQry.Response> {

    override fun exec(request: UniqueVideoHlsKeyTokenQry.Request): UniqueVideoHlsKeyTokenQry.Response {
        return UniqueVideoHlsKeyTokenQry.Response(
            exists = TODO("set exists")
        )
    }
}
