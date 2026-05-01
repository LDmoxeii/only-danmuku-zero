package edu.only4.danmuku.domain.aggregates.video_quality_policy

import edu.only4.danmuku.domain.aggregates.video_quality_policy.enums.QualityAuthPolicy

fun VideoQualityPolicy.applyPolicy(
    authPolicy: QualityAuthPolicy,
    remark: String?,
) {
    this.authPolicy = authPolicy
    this.remark = remark
}
