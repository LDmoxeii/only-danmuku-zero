package {{ basePackage }}.adapter.portal.api.admin

import cn.dev33.satoken.annotation.SaIgnore
import com.only.engine.exception.BusinessException
import {{ basePackage }}.domain.shared.error.DanmukuBusinessErrors
import com.only.engine.web.annotation.IgnoreResultWrapper
import com.only4.cap4k.ddd.core.Mediator
import {{ basePackage }}.adapter.portal.api.payload.admin_video_abr.GetVariants
import {{ basePackage }}.application.distributed.clients.oss.ReadObjectAsTextCli
import {{ basePackage }}.application.queries.file_storage.GetResourceAccessUrlQry
import {{ basePackage }}.application.queries.video_transcode.GetVideoAbrMasterQry
import {{ basePackage }}.application.queries.video_transcode.GetVideoFilePostPathQry
import {{ basePackage }}.application.queries.video_transcode.ListVideoAbrVariantsQry
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@SaIgnore
@RestController
@RequestMapping("/admin/video/abr")
class AdminVideoAbrController {

    @IgnoreResultWrapper
    @GetMapping("/videoResource/{fileId}/master.m3u8")
    fun master(@PathVariable fileId: Long): ResponseEntity<String> {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/getVariants")
    fun getVariants(@RequestBody request: GetVariants.Request): GetVariants.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @IgnoreResultWrapper
    @GetMapping("/videoResource/{fileId}/{quality}/index.m3u8")
    fun playlist(
        @PathVariable fileId: Long,
        @PathVariable quality: String,
    ): ResponseEntity<String> {
        TODO("Pending controller adapter contract implementation.")
    }

    @IgnoreResultWrapper
    @GetMapping("/videoResource/{fileId}/{quality}/{ts}")
    fun segment(
        @PathVariable fileId: Long,
        @PathVariable quality: String,
        @PathVariable ts: String,
    ): ResponseEntity<Void> {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun resolveUrl(base: String, suffix: String): String {
        TODO("Pending controller adapter contract implementation.")
    }
}
