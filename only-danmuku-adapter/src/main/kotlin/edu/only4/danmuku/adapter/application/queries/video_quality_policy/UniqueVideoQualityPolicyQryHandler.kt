package edu.only4.danmuku.adapter.application.queries.video_quality_policy

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_quality_policy.UniqueVideoQualityPolicyQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class UniqueVideoQualityPolicyQryHandler : Query<UniqueVideoQualityPolicyQry.Request, UniqueVideoQualityPolicyQry.Response> {

    override fun exec(request: UniqueVideoQualityPolicyQry.Request): UniqueVideoQualityPolicyQry.Response {
        return UniqueVideoQualityPolicyQry.Response(
            exists = TODO("set exists")
        )
    }
}
