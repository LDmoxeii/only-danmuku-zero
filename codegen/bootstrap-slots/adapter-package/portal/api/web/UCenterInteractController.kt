package {{ basePackage }}.adapter.portal.api.web

import com.only.engine.satoken.utils.LoginHelper
import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.share.PageData
import {{ basePackage }}.adapter.portal.api.payload.u_center_interact.DeleteComment
import {{ basePackage }}.adapter.portal.api.payload.u_center_interact.DeleteDanmuku
import {{ basePackage }}.adapter.portal.api.payload.u_center_interact.GetAllVideoList
import {{ basePackage }}.adapter.portal.api.payload.u_center_interact.GetCommentPage
import {{ basePackage }}.adapter.portal.api.payload.u_center_interact.GetDanmukuPage
import {{ basePackage }}.application.commands.video_comment.DeleteVideoCommentCmd
import {{ basePackage }}.application.commands.video_danmuku.DeleteVideoDanmukuCmd
import {{ basePackage }}.application.queries.video.GetVideoAllList
import {{ basePackage }}.application.queries.video_comment.VideoCommentPageQry
import {{ basePackage }}.application.queries.video_danmuku.GetVideoDanmukuPageQry
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/uCenter")
class UCenterInteractController {

    @PostMapping("/getAllVideoList")
    fun getAllVideoList(): List<GetAllVideoList.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/getCommentPage")
    fun getCommentPage(@RequestBody @Validated request: GetCommentPage.Request): PageData<GetCommentPage.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/delComment")
    fun deleteComment(@RequestBody @Validated request: DeleteComment.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
    @PostMapping("/getDanmukuPage")
    fun getDanmukuPage(@RequestBody @Validated request: GetDanmukuPage.Request): PageData<GetDanmukuPage.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/deleteDanmuku")
    fun deleteDanmuku(@RequestBody @Validated request: DeleteDanmuku.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
}
