package {{ basePackage }}.adapter.portal.api.web

import cn.dev33.satoken.annotation.SaIgnore
import com.only.engine.web.annotation.IgnoreResultWrapper
import {{ basePackage }}.adapter.portal.api.payload.video_abr.GetVideoVariants
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@SaIgnore
@RestController
@RequestMapping("/video/abr")
class VideoAbrController {

    @IgnoreResultWrapper
    @GetMapping("/videoResource/{fileId}/master.m3u8")
    fun master(@PathVariable fileId: Long): ResponseEntity<String> {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/variants")
    fun variants(@RequestBody request: GetVideoVariants.Request): GetVideoVariants.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @IgnoreResultWrapper
    @GetMapping("/videoResource/{fileId}/{quality}/index.m3u8")
    fun playlist(@PathVariable fileId: Long, @PathVariable quality: String): ResponseEntity<String> {
        TODO("Pending controller adapter contract implementation.")
    }

    @IgnoreResultWrapper
    @GetMapping("/videoResource/{fileId}/{quality}/{ts}")
    fun segment(
        @PathVariable fileId: Long,
        @PathVariable quality: String,
        @PathVariable ts: String
    ): ResponseEntity<Void> {
        TODO("Pending controller adapter contract implementation.")
    }
}
