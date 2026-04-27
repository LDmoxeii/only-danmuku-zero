package {{ basePackage }}.adapter.portal.api.admin

import com.only4.cap4k.ddd.core.share.PageData
import {{ basePackage }}.adapter.portal.api.payload.admin_interact.DeleteComment
import {{ basePackage }}.adapter.portal.api.payload.admin_interact.DeleteDanmuku
import {{ basePackage }}.adapter.portal.api.payload.admin_interact.GetDanmukuPage
import {{ basePackage }}.adapter.portal.api.payload.admin_interact.GetVideoCommentPage
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/interact")
class AdminInteractController {

    @PostMapping("/page")
    fun page(@RequestBody @Validated request: GetDanmukuPage.Request): PageData<GetDanmukuPage.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/delDanmuku")
    fun deleteDanmuku(@RequestBody @Validated request: DeleteDanmuku.Request) {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/getVideoCommentPage")
    fun getVideoCommentPage(@RequestBody @Validated request: GetVideoCommentPage.Request): PageData<GetVideoCommentPage.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/delComment")
    fun deleteComment(@RequestBody @Validated request: DeleteComment.Request) {
        TODO("Pending controller adapter contract implementation.")
    }

}
