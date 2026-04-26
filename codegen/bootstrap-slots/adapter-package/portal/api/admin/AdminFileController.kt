package {{ basePackage }}.adapter.portal.api.admin

import cn.dev33.satoken.annotation.SaIgnore
import com.only.engine.web.annotation.IgnoreResultWrapper
import com.only4.cap4k.ddd.core.Mediator
import {{ basePackage }}.application.distributed.clients.file_storage.UploadImageResourceCli
import {{ basePackage }}.application.queries.file_storage.GetResourceAccessUrlQry
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.net.URI

/**
 * 管理员文件管理控制器
 */
@RestController
@RequestMapping("/admin/file")
@Validated
class AdminFileController {

    @PostMapping("/uploadImage")
    fun uploadImage(
        file: MultipartFile,
        createThumbnail: Boolean,
    ): String {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @IgnoreResultWrapper
    @GetMapping("/getResource")
    fun getResource(@RequestParam sourceName: String): ResponseEntity<Void> {
        TODO("Pending controller adapter contract implementation.")
    }

}
