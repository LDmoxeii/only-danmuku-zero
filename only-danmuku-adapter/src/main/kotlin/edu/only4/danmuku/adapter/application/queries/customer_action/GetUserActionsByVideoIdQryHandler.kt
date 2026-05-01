package edu.only4.danmuku.adapter.application.queries.customer_action

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.CustomerAction
import edu.only4.danmuku.application.queries._share.model.customer
import edu.only4.danmuku.application.queries._share.model.fetchBy
import edu.only4.danmuku.application.queries._share.model.id
import edu.only4.danmuku.application.queries._share.model.video
import edu.only4.danmuku.application.queries.customer_action.GetUserActionsByVideoIdQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

/**
 * 获取指定用户在指定视频下的行为列表
 */
@Service
class GetUserActionsByVideoIdQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetUserActionsByVideoIdQry.Request, GetUserActionsByVideoIdQry.Response> {

    override fun exec(request: GetUserActionsByVideoIdQry.Request): GetUserActionsByVideoIdQry.Response {
        val actions = sqlClient.createQuery(CustomerAction::class) {
            where(table.customer.id eq request.userId)
            where(table.video.id eq request.videoId)
            select(
                table.fetchBy {
                    allScalarFields()
                    customer()
                    videoOwner()
                    video {
                        videoName()
                        videoCover()
                    }
                    comment()
                }
            )
        }.execute()

        return GetUserActionsByVideoIdQry.Response(
            items = actions.map {
                GetUserActionsByVideoIdQry.Response.ActionItem(
                    actionId = it.id,
                    userId = it.customer.id,
                    videoId = it.video!!.id,
                    videoName = it.video!!.videoName,
                    videoCover = it.video!!.videoCover,
                    videoUserId = it.videoOwner.id,
                    commentId = it.comment?.id,
                    actionType = it.actionType,
                    actionCount = it.actionCount,
                    actionTime = it.actionTime,
                )
            }
        )
    }
}
