package edu.only4.danmuku.adapter.application.queries.video_storage

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_storage.GetVideoHlsResourceUrlQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetVideoHlsResourceUrlQryHandler : Query<GetVideoHlsResourceUrlQry.Request, GetVideoHlsResourceUrlQry.Response> {

    override fun exec(request: GetVideoHlsResourceUrlQry.Request): GetVideoHlsResourceUrlQry.Response {
        return GetVideoHlsResourceUrlQry.Response(
            url = TODO("set url"),
            contentType = TODO("set contentType")
        )
    }
}
