package edu.only4.danmuku.adapter.application.queries.video_danmuku

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_danmuku.GetVideoDanmukuPageQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetVideoDanmukuPageQryHandler : Query<GetVideoDanmukuPageQry.Request, GetVideoDanmukuPageQry.Response> {

    override fun exec(request: GetVideoDanmukuPageQry.Request): GetVideoDanmukuPageQry.Response {
        return GetVideoDanmukuPageQry.Response(
            danmukuId = TODO("set danmukuId"),
            videoId = TODO("set videoId"),
            videoName = TODO("set videoName"),
            videoCover = TODO("set videoCover"),
            customerId = TODO("set customerId"),
            customerNickname = TODO("set customerNickname"),
            text = TODO("set text"),
            mode = TODO("set mode"),
            color = TODO("set color"),
            time = TODO("set time"),
            postTime = TODO("set postTime")
        )
    }
}
