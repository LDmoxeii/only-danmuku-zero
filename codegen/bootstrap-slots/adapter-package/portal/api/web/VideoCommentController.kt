package {{ basePackage }}.adapter.portal.api.web

import cn.dev33.satoken.annotation.SaIgnore
import com.only.engine.satoken.utils.LoginHelper
import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.share.PageData
import {{ basePackage }}.adapter.portal.api.payload.video_comment.CancelTopComment
import {{ basePackage }}.adapter.portal.api.payload.video_comment.DeleteComment
import {{ basePackage }}.adapter.portal.api.payload.video_comment.PostComment
import {{ basePackage }}.adapter.portal.api.payload.video_comment.GetCommentPage
import {{ basePackage }}.adapter.portal.api.payload.video_comment.TopComment
import {{ basePackage }}.application.commands.video_comment.DeleteVideoCommentCmd
import {{ basePackage }}.application.commands.video_comment.PostCommentCmd
import {{ basePackage }}.application.commands.video_comment.TopCommentCmd
import {{ basePackage }}.application.commands.video_comment.UntopCommentCmd
import {{ basePackage }}.application.queries.customer_action.GetUserActionsByVideoIdQry
import {{ basePackage }}.application.queries.video_comment.GetCommentByIdQry
import {{ basePackage }}.application.queries.video_comment.VideoCommentPageQry
import {{ basePackage }}.domain.aggregates.customer_action.enums.ActionType
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
        comment: VideoCommentPageQry.Response,
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
