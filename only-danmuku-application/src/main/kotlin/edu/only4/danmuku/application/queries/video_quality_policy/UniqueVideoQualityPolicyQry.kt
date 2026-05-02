
package edu.only4.danmuku.application.queries.video_quality_policy

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam

object UniqueVideoQualityPolicyQry {

    data class Request(
        val videoId: UUID,
        val fileIndex: Int,
        val quality: String,
        val excludeVideoQualityPolicyId: UUID?
    ) : RequestParam<Response>

    data class Response(
        val exists: Boolean
    )

}

