package edu.only4.danmuku.application.queries.video_quality_policy

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoQualityPolicyQry {

    data class Request(
        val videoId: Long,
        val fileIndex: Int,
        val quality: String,
        val excludeVideoQualityPolicyId: Long?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}
