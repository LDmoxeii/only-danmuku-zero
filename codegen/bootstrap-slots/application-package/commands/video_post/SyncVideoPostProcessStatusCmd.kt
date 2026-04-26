package {{ basePackage }}.application.commands.video_post

import com.only.engine.error.CommonErrors
import com.only.engine.exception.AppException
import com.only.engine.exception.BusinessException
import com.only.engine.exception.DependencyException
import com.only.engine.exception.RequestException
import com.only.engine.exception.SystemException
import {{ basePackage }}.domain.shared.error.DanmukuBusinessErrors
import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.command.Command

import {{ basePackage }}.domain._share.meta.video_post.SVideoPost
import {{ basePackage }}.domain.aggregates.video_post.VideoFilePostVariant
import {{ basePackage }}.domain.aggregates.video_post.enums.EncryptMethod
import {{ basePackage }}.domain.aggregates.video_post.enums.VideoStatus
import kotlin.jvm.optionals.getOrNull

import org.springframework.stereotype.Service

/**
 * 处理聚合事件驱动：更新稿件整体状态并回填稿件文件结果
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2026/01/05
 */
object SyncVideoPostProcessStatusCmd {

    @Service
    class Handler : Command<Request, Response> {
        override fun exec(request: Request): Response {
            TODO("Implement video post process status synchronization after aggregate behavior generation is available.")
        }

    }

    data class Request(
        val videoPostId: Long,
        val targetStatus: VideoStatus,
        val duration: Int?,
        val failReason: String?,
        val fileList: List<FileItem> = emptyList()
    ) : RequestParam<Response>

    data class FileItem(
        val fileIndex: Int,
        val transcodeOutputPrefix: String?,
        val encryptOutputPrefix: String?,
        val variants: List<VariantItem> = emptyList(),
        val duration: Int?,
        val fileSize: Long?,
        val encryptMethod: String?,
        val keyVersion: Int?
    )

    data class Response(
        val success: Boolean = true
    )

    data class VariantItem(
        val quality: String = "",
        val width: Int = 0,
        val height: Int = 0,
        val videoBitrateKbps: Int = 0,
        val audioBitrateKbps: Int = 0,
        val bandwidthBps: Int = 0,
        val playlistPath: String = "",
        val segmentPrefix: String? = null,
        val segmentDuration: Int? = null,
    )
}
