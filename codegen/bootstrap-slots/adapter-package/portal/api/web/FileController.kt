package {{ basePackage }}.adapter.portal.api.web

import cn.dev33.satoken.annotation.SaIgnore
import com.only.engine.satoken.utils.LoginHelper
import com.only.engine.web.annotation.IgnoreResultWrapper
import com.only4.cap4k.ddd.core.Mediator
import {{ basePackage }}.adapter.portal.api.payload.file.DeleteUploadSession
import {{ basePackage }}.adapter.portal.api.payload.file.PreUploadVideo
import {{ basePackage }}.adapter.portal.api.payload.file.UploadVideo
import {{ basePackage }}.application.commands.file_upload_session.CreateUploadSessionCmd
import {{ basePackage }}.application.commands.file_upload_session.DeleteUploadSessionCmd
import {{ basePackage }}.application.commands.file_upload_session.UploadVideoChunkCmd
import {{ basePackage }}.application.distributed.clients.file_storage.UploadImageResourceCli
import {{ basePackage }}.application.distributed.clients.file_upload_session.UploadVideoChunkStorageCli
import {{ basePackage }}.application.queries.file_storage.GetResourceAccessUrlQry
import {{ basePackage }}.application.queries.video_transcode.GetUploadSessionTempPathQry
import jakarta.validation.constraints.NotEmpty
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.net.URI

/**
 * 文件操作控制器 - 处理文件上传、资源获取等操作
 */
@RestController
@RequestMapping("/file")
@Validated
class FileController {

    @SaIgnore
    @IgnoreResultWrapper
    @GetMapping("/getResource")
    fun getResource(
        @NotEmpty sourceName: String
    ): ResponseEntity<Unit> {
        TODO("Pending controller adapter contract implementation.")
    }

    /**
     * 预上传视频
     */
    @PostMapping("/preUploadVideo")
    fun preUploadVideo(@RequestBody @Validated request: PreUploadVideo.Request): Long {
        TODO("Pending controller adapter contract implementation.")
    }

    /**
     * 上传视频分片
     */
    @PostMapping("/uploadVideo")
    fun uploadVideo(
        @RequestPart("chunkFile") chunkFile: MultipartFile,
        @RequestParam("chunkIndex") chunkIndex: Int,
        @RequestParam("uploadId") uploadId: Long,
    ): UploadVideo.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    /**
     * 删除上传中的视频
     */
    @PostMapping("/deleteUploadSession")
    fun deleteUploadSession(@RequestBody @Validated request: DeleteUploadSession.Request) {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/uploadImage")
    fun uploadImage(
        file: MultipartFile,
        createThumbnail: Boolean,
    ): String {
        TODO("Pending controller adapter contract implementation.")
    }
}
