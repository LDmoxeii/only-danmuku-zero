package edu.only4.danmuku.adapter.portal.api.web

import cn.dev33.satoken.annotation.SaIgnore
import edu.only4.danmuku.adapter.portal.api.payload.video_danmuku.GetDanmukuList
import edu.only4.danmuku.adapter.portal.api.payload.video_danmuku.PostDanmuku
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/danmuku")
class VideoDanmukuController {

    /**
     * 加载弹幕列表
     */
    @SaIgnore
    @PostMapping("/getList")
    fun getList(@RequestBody @Validated request: GetDanmukuList.Request): List<GetDanmukuList.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/postDanmuku")
    fun postDanmuku(@RequestBody @Validated request: PostDanmuku.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
}
