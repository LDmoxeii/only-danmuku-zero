

package edu.only4.danmuku.application.queries.video_comment

import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.query.PageRequest
import com.only4.cap4k.ddd.core.share.PageData

object VideoCommentPageQry {

    data class Request(
        override var pageNum: Int = 1,
        override var pageSize: Int = 10,

        var videoId: Long? = null,
        var videoUserId: Long? = null,
        var videoNameFuzzy: String? = null
    ) : PageRequest, RequestParam<Response>

    data class Response(
        val page: PageData<CommentItem>
    ) {
        data class CommentItem(
            val commentId: Long,
            val parentCommentId: Long,
            val videoId: Long,
            val videoUserId: Long,
            val videoName: String,
            val videoCover: String,
            val content: String?,
            val imgPath: String?,
            val customerId: Long,
            val customerNickname: String,
            val customerAvatar: String?,
            val replyCustomerId: Long?,
            val replyCustomerNickname: String?,
            val postTime: Long,
            val likeCount: Int? = 0,
            val hateCount: Int? = 0,
            val topType: Int? = 0,
            val childrenCount: Int = 0,
            val children: List<Children>?
        )
        data class Children(
            val commentId: Long,
            val parentCommentId: Long,
            val videoId: Long,
            val videoUserId: Long,
            val videoName: String,
            val videoCover: String,
            val content: String?,
            val imgPath: String?,
            val customerId: Long,
            val customerNickname: String,
            val customerAvatar: String?,
            val replyCustomerId: Long?,
            val replyCustomerNickname: String?,
            val postTime: Long,
            val likeCount: Int? = 0,
            val hateCount: Int? = 0,
            val topType: Int? = 0,
            val childrenCount: Int = 0,
            val children: List<Children>?
        )
    }

}
