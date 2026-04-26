package edu.only4.danmuku.adapter.application.queries.customer_action

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.customer_action.GetUserActionsByVideoIdQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetUserActionsByVideoIdQryHandler : Query<GetUserActionsByVideoIdQry.Request, GetUserActionsByVideoIdQry.Response> {

    override fun exec(request: GetUserActionsByVideoIdQry.Request): GetUserActionsByVideoIdQry.Response {
        return GetUserActionsByVideoIdQry.Response(
            actionId = TODO("set actionId"),
            userId = TODO("set userId"),
            videoId = TODO("set videoId"),
            videoName = TODO("set videoName"),
            videoCover = TODO("set videoCover"),
            videoUserId = TODO("set videoUserId"),
            commentId = TODO("set commentId"),
            actionType = TODO("set actionType"),
            actionCount = TODO("set actionCount"),
            actionTime = TODO("set actionTime")
        )
    }
}
