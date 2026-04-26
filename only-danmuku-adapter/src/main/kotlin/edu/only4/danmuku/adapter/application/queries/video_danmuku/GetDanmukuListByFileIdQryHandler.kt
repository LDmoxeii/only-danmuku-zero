package edu.only4.danmuku.adapter.application.queries.video_danmuku

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_danmuku.GetDanmukuListByFileIdQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetDanmukuListByFileIdQryHandler : Query<GetDanmukuListByFileIdQry.Request, GetDanmukuListByFileIdQry.Response> {

    override fun exec(request: GetDanmukuListByFileIdQry.Request): GetDanmukuListByFileIdQry.Response {
        return GetDanmukuListByFileIdQry.Response(
            danmukuId = TODO("set danmukuId"),
            fileId = TODO("set fileId"),
            videoId = TODO("set videoId"),
            userId = TODO("set userId"),
            text = TODO("set text"),
            mode = TODO("set mode"),
            color = TODO("set color"),
            time = TODO("set time"),
            postTime = TODO("set postTime"),
            videoName = TODO("set videoName"),
            videoCover = TODO("set videoCover"),
            nickName = TODO("set nickName")
        )
    }
}
