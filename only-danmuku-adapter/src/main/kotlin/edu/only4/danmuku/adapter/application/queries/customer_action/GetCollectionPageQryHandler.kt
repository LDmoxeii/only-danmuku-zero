package edu.only4.danmuku.adapter.application.queries.customer_action

import com.only4.cap4k.ddd.core.application.query.PageQuery
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.application.queries.customer_action.GetCollectionPageQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetCollectionPageQryHandler : PageQuery<GetCollectionPageQry.Request, GetCollectionPageQry.Response> {

    override fun exec(request: GetCollectionPageQry.Request): PageData<GetCollectionPageQry.Response> {
        return PageData.create(request, 1L, listOf(
            GetCollectionPageQry.Response(
                actionId = TODO("set actionId"),
                videoId = TODO("set videoId"),
                videoUserId = TODO("set videoUserId"),
                commentId = TODO("set commentId"),
                actionType = TODO("set actionType"),
                actionCount = TODO("set actionCount"),
                userId = TODO("set userId"),
                actionTime = TODO("set actionTime"),
                videoName = TODO("set videoName"),
                videoCover = TODO("set videoCover")
            )
        ))
    }
}
