package edu.only4.danmuku.adapter.application.queries.video_danmuku

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_danmuku.GetDanmukuOwnerQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetDanmukuOwnerQryHandler : Query<GetDanmukuOwnerQry.Request, GetDanmukuOwnerQry.Response> {

    override fun exec(request: GetDanmukuOwnerQry.Request): GetDanmukuOwnerQry.Response {
        return GetDanmukuOwnerQry.Response(
            videoId = TODO("set videoId"),
            ownerId = TODO("set ownerId")
        )
    }
}
