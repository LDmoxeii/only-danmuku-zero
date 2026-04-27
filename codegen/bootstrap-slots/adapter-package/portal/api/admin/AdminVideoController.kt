package {{ basePackage }}.adapter.portal.api.admin

import com.only4.cap4k.ddd.core.share.PageData
import {{ basePackage }}.adapter.portal.api.payload.admin_video.*
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 管理员视频管理控制器
 */
@RestController
@RequestMapping("/admin/video")
@Validated
class AdminVideoController {

    @PostMapping("/page")
    fun page(@RequestBody @Validated request: GetVideoPage.Request): PageData<GetVideoPage.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/recommendVideo")
    fun recommendVideo(@RequestBody @Validated request: RecommendVideo.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
    @PostMapping("/auditVideo")
    fun auditVideo(@RequestBody @Validated request: AuditVideo.Request) {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/deleteVideo")
    fun deleteVideo(@RequestBody @Validated request: DeleteVideo.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
    @PostMapping("/getVideoPList")
    fun getVideoPList(@RequestBody @Validated request: GetVideoPlist.Request): List<GetVideoPlist.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

}
