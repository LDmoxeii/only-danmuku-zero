package edu.only4.danmuku.adapter.portal.api.web

import cn.dev33.satoken.annotation.SaIgnore
import com.only.engine.web.annotation.IgnoreResultWrapper
import edu.only4.danmuku.adapter.portal.api.payload.video_encrypt.GetVideEncVariants
import edu.only4.danmuku.adapter.portal.api.payload.video_encrypt.IssueToken
import edu.only4.danmuku.application.queries.video.GetVideoFileContextByIdQry
import edu.only4.danmuku.application.queries.video_encrypt.GetVideoEncryptStatusQry
import edu.only4.danmuku.domain.aggregates.video_quality_policy.enums.QualityAuthPolicy
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
