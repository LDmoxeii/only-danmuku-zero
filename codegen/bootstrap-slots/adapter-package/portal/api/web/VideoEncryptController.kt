package {{ basePackage }}.adapter.portal.api.web

import cn.dev33.satoken.annotation.SaIgnore
import cn.dev33.satoken.stp.StpUtil
import com.only.engine.exception.BusinessException
import {{ basePackage }}.domain.shared.error.DanmukuBusinessErrors
import com.only.engine.json.misc.JsonUtils
import com.only.engine.oss.factory.OssFactory
import com.only.engine.web.annotation.IgnoreResultWrapper
import com.only4.cap4k.ddd.core.Mediator
import {{ basePackage }}.adapter.portal.api.payload.video_encrypt.IssueToken
import {{ basePackage }}.adapter.portal.api.payload.video_encrypt.GetVideEncVariants
import {{ basePackage }}.application.commands.video_encrypt.ConsumeVideoHlsKeyTokenCmd
import {{ basePackage }}.application.commands.video_encrypt.IssueVideoHlsKeyTokenCmd
import {{ basePackage }}.application.queries.file_storage.GetResourceAccessUrlQry
import {{ basePackage }}.application.queries.video_encrypt.GetLatestVideoHlsKeyVersionQry
import {{ basePackage }}.application.queries.video_encrypt.GetVideoEncryptStatusQry
import {{ basePackage }}.application.queries.video_encrypt.ListVideoQualityAuthQry
import {{ basePackage }}.application.queries.video.GetVideoFileContextByIdQry
import {{ basePackage }}.application.queries.video.ListVideoFileVariantsQry
import {{ basePackage }}.domain.aggregates.video_quality_policy.enums.QualityAuthPolicy
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.nio.charset.StandardCharsets

@SaIgnore
@RestController
@RequestMapping("/video/enc")
class VideoEncryptController {

    @PostMapping("/issueToken")
    fun issueToken(@RequestBody request: IssueToken.Request): IssueToken.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/variants")
    fun variants(@RequestBody request: GetVideEncVariants.Request): GetVideEncVariants.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @IgnoreResultWrapper
    @GetMapping("/videoResource/{fileId}/master.m3u8")
    fun master(
        @PathVariable fileId: Long,
        @RequestParam token: String
    ): ResponseEntity<String> {
        TODO("Pending controller adapter contract implementation.")
    }

    @IgnoreResultWrapper
    @GetMapping("/videoResource/{fileId}/{quality}/index.m3u8")
    fun playlist(
        @PathVariable fileId: Long,
        @PathVariable quality: String,
        @RequestParam token: String
    ): ResponseEntity<String> {
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

    @IgnoreResultWrapper
    @GetMapping("/key")
    fun keyByGet(
        @RequestParam token: String,
        @RequestParam keyId: String,
        @RequestParam(required = false) quality: String?
    ): ResponseEntity<ByteArrayResource> {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun encryptStatus(videoFileId: Long): GetVideoEncryptStatusQry.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun hexToBytes(hex: String): ByteArray {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun loadQualityPolicies(videoFileId: Long): List<PolicyPayload> {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun loadAbrQualities(videoFileId: Long): List<String> {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun resolveFileContext(videoFileId: Long): GetVideoFileContextByIdQry.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun resolveAllowedQualities(videoFileId: Long): List<String> {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun resolvePolicy(code: Int?): QualityAuthPolicy {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun isPlayable(policy: QualityAuthPolicy, allowLogin: Boolean): Boolean {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun sortPolicies(policies: List<PolicyPayload>): List<PolicyPayload> {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun qualityScore(quality: String): Int {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun filterMasterByAllowedQualities(content: String, allowedQualities: Set<String>): String {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun attachTokenToVariantPlaylists(content: String, token: String): String {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun readObjectAsText(objectKey: String): String {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun playlistAllowed(playlist: String, allowedQualities: Set<String>): Boolean {
        TODO("Pending controller adapter contract implementation.")
    }

    private fun computeAllowedQualities(videoFileId: Long): String? {
        TODO("Pending controller adapter contract implementation.")
    }

    data class PolicyPayload(
        val quality: String,
        val authPolicy: Int
    )

    companion object {
        private val QUALITY_NUMBER_REGEX = Regex("(\\d+)")
    }
}
