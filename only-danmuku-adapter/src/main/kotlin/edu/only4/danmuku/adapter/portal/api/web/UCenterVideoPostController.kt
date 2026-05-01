package edu.only4.danmuku.adapter.portal.api.web

import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.adapter.portal.api.payload.u_center_video_post.*
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
