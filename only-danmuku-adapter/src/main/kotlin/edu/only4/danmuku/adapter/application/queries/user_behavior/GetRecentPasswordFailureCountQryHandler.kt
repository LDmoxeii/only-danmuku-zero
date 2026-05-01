package edu.only4.danmuku.adapter.application.queries.user_behavior

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.user_behavior.GetRecentPasswordFailureCountQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetRecentPasswordFailureCountQryHandler : Query<GetRecentPasswordFailureCountQry.Request, GetRecentPasswordFailureCountQry.Response> {

    override fun exec(request: GetRecentPasswordFailureCountQry.Request): GetRecentPasswordFailureCountQry.Response {
        return GetRecentPasswordFailureCountQry.Response(
            failureCount = TODO("set failureCount")
        )
    }
}
