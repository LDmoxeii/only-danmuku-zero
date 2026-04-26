package {{ basePackage }}.adapter.portal.api.web

import com.only.engine.exception.BusinessException
import {{ basePackage }}.domain.shared.error.DanmukuBusinessErrors
import com.only.engine.json.misc.JsonUtils
import com.only.engine.satoken.utils.LoginHelper
import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.share.PageData
import {{ basePackage }}.adapter.portal.api.payload.u_center_video_post.DeleteVideo
import {{ basePackage }}.adapter.portal.api.payload.u_center_video_post.SaveVideoPost
import {{ basePackage }}.adapter.portal.api.payload.u_center_video_post.SaveVideoPostInteraction
import {{ basePackage }}.adapter.portal.api.payload.u_center_video_post.GetVideoByVideoId
import {{ basePackage }}.adapter.portal.api.payload.u_center_video_post.GetVideoPostCountInfo
import {{ basePackage }}.adapter.portal.api.payload.u_center_video_post.GetVideoPostPage
import {{ basePackage }}.adapter.portal.api.payload.u_center_video_post.UpdateVideoPost
import {{ basePackage }}.application.commands.video_post.ChangeVideoPostInteractionCmd
import {{ basePackage }}.application.commands.video_post.CreateVideoPostCmd
import {{ basePackage }}.application.commands.video_post.DeleteVideoPostCmd
import {{ basePackage }}.application.commands.video_post.UpdateVideoPostCmd
import {{ basePackage }}.application.queries.video_draft.GetVideoDraftCountByStatusQry
import {{ basePackage }}.application.queries.video_draft.GetVideoPostInfoQry
import {{ basePackage }}.domain.aggregates.video_post.enums.VideoStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/uCenter")
class UCenterVideoPostController {

    @PostMapping("/videoPost/save")
    fun save(@RequestBody @Validated request: SaveVideoPost.Request): SaveVideoPost.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/videoPost//update")
    fun update(@RequestBody @Validated request: UpdateVideoPost.Request): UpdateVideoPost.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/videoPost/getPage")
    fun getPage(@RequestBody @Validated request: GetVideoPostPage.Request): PageData<GetVideoPostPage.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/videoPost/getCountInfo")
    fun getCountInfo(): GetVideoPostCountInfo.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/getVideoByVideoId")
    fun getVideoByVideoId(@RequestBody @Validated request: GetVideoByVideoId.Request): GetVideoByVideoId.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/videoPost/saveInteraction")
    fun saveInteraction(@RequestBody @Validated request: SaveVideoPostInteraction.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
    @PostMapping("videoPost/delete")
    fun delete(@RequestBody @Validated request: DeleteVideo.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
}
