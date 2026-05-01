package edu.only4.danmuku.adapter.application.queries.video_comment

import com.only4.cap4k.ddd.core.application.query.Query
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.application.queries._share.model.VideoComment
import edu.only4.danmuku.application.queries._share.model.customerId
import edu.only4.danmuku.application.queries._share.model.fetchBy
import edu.only4.danmuku.application.queries._share.model.parentId
import edu.only4.danmuku.application.queries._share.model.postTime
import edu.only4.danmuku.application.queries._share.model.topType
import edu.only4.danmuku.application.queries._share.model.video
import edu.only4.danmuku.application.queries._share.model.videoId
import edu.only4.danmuku.application.queries._share.model.videoName
import edu.only4.danmuku.application.queries.video_comment.VideoCommentPageQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.asc
import org.babyfish.jimmer.sql.kt.ast.expression.case
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.`eq?`
import org.babyfish.jimmer.sql.kt.ast.expression.`ilike?`
import org.springframework.stereotype.Service

/**
 * 评论分页
 */
@Service
class VideoCommentPageQryHandler(
    private val sqlClient: KSqlClient,
) : Query<VideoCommentPageQry.Request, VideoCommentPageQry.Response> {

    override fun exec(request: VideoCommentPageQry.Request): VideoCommentPageQry.Response {
        val pageResult = sqlClient.createQuery(VideoComment::class) {
            where(table.videoId `eq?` request.videoId)
            where(table.video.customerId `eq?` request.videoUserId)
            where(table.video.videoName `ilike?` request.videoNameFuzzy)
            orderBy(
                case()
                    .match(table.topType.eq(1), 0)
                    .otherwise(1)
                    .asc(),
                table.postTime.desc()
            )
            select(table.fetchBy {
                parentId()
                content()
                imgPath()
                postTime()
                likeCount()
                hateCount()
                topType()
                video {
                    customerId()
                    videoName()
                    videoCover()
                }
                customer {
                    relation {
                        nickName()
                        avatar()
                    }
                }
                replyCustomer {
                    nickName()
                }
            })
        }.fetchPage(request.pageNum - 1, request.pageSize)

        return VideoCommentPageQry.Response(
            page = PageData.create(
                pageNum = request.pageNum,
                pageSize = request.pageSize,
                list = pageResult.rows.map { toCommentItem(it, loadChildren = true) },
                totalCount = pageResult.totalRowCount
            )
        )
    }

    private fun toCommentItem(item: VideoComment, loadChildren: Boolean): VideoCommentPageQry.Response.CommentItem {
        val children = if (loadChildren) loadChildComments(item.id) else emptyList()
        return VideoCommentPageQry.Response.CommentItem(
            commentId = item.id,
            parentCommentId = item.parentId ?: 0,
            videoId = item.video.id,
            videoUserId = item.video.customerId,
            videoName = item.video.videoName,
            videoCover = item.video.videoCover,
            content = item.content,
            imgPath = item.imgPath,
            customerId = item.customer.id,
            customerNickname = item.customer.relation!!.nickName,
            customerAvatar = item.customer.relation!!.avatar,
            replyCustomerId = item.replyCustomer?.id,
            replyCustomerNickname = item.replyCustomer?.nickName,
            postTime = item.postTime,
            likeCount = item.likeCount,
            hateCount = item.hateCount,
            topType = item.topType,
            childrenCount = children.size,
            children = children.ifEmpty { null }
        )
    }

    private fun toChild(item: VideoComment, loadChildren: Boolean): VideoCommentPageQry.Response.Children {
        val children = if (loadChildren) loadChildComments(item.id) else emptyList()
        return VideoCommentPageQry.Response.Children(
            commentId = item.id,
            parentCommentId = item.parentId ?: 0,
            videoId = item.video.id,
            videoUserId = item.video.customerId,
            videoName = item.video.videoName,
            videoCover = item.video.videoCover,
            content = item.content,
            imgPath = item.imgPath,
            customerId = item.customer.id,
            customerNickname = item.customer.relation!!.nickName,
            customerAvatar = item.customer.relation!!.avatar,
            replyCustomerId = item.replyCustomer?.id,
            replyCustomerNickname = item.replyCustomer?.nickName,
            postTime = item.postTime,
            likeCount = item.likeCount,
            hateCount = item.hateCount,
            topType = item.topType,
            childrenCount = children.size,
            children = children.ifEmpty { null }
        )
    }

    private fun loadChildComments(parentCommentId: Long): List<VideoCommentPageQry.Response.Children> {
        val childComments = sqlClient.createQuery(VideoComment::class) {
            where(table.parentId eq parentCommentId)
            orderBy(table.postTime.asc())
            select(table.fetchBy {
                parentId()
                content()
                imgPath()
                postTime()
                likeCount()
                hateCount()
                topType()
                video {
                    customerId()
                    videoName()
                    videoCover()
                }
                customer {
                    relation {
                        nickName()
                        avatar()
                    }
                }
                replyCustomer {
                    nickName()
                }
            })
        }.execute()
        return childComments.map { toChild(it, loadChildren = true) }
    }
}
