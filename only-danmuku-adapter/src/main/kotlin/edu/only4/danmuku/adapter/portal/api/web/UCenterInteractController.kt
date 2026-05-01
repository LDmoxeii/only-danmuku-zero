package edu.only4.danmuku.adapter.portal.api.web

import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.adapter.portal.api.payload.u_center_interact.*
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
