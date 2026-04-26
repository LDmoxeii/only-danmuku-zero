package edu.only4.danmuku.application.queries.video_quality_policy

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoQualityPolicyVideoIdFileIndexQualityDeletedQry {

    data class Request(
        val videoId: Long,
        val fileIndex: Int,
        val quality: String,
        val deleted: Long,
        val excludeVideoQualityPolicyId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )
}
