package edu.only4.danmuku.adapter.portal.api.web

import cn.dev33.satoken.annotation.SaIgnore
import edu.only4.danmuku.adapter.portal.api.payload.video_comment.*
import edu.only4.danmuku.application.queries.video_comment.VideoCommentPageQry
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/comment")
class VideoCommentController {

    @SaIgnore
    @PostMapping("/page")
    fun page(@RequestBody @Validated request: GetCommentPage.Request): GetCommentPage.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun coverToCommentItem(
        comment: VideoCommentPageQry.Response.CommentItem,
        likedCommentIds: Set<Long>
    ): GetCommentPage.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/post")
    fun post(@RequestBody @Validated request: PostComment.Request) {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/delete")
    fun delete(@RequestBody @Validated request: DeleteComment.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
    @PostMapping("/top")
    fun top(@RequestBody @Validated request: TopComment.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
    @PostMapping("/cancelTop")
    fun cancelTop(@RequestBody @Validated request: CancelTopComment.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
}
