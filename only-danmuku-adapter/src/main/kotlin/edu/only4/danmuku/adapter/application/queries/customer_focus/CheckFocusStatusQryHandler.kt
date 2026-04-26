package edu.only4.danmuku.adapter.application.queries.customer_focus

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.customer_focus.CheckFocusStatusQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class CheckFocusStatusQryHandler : Query<CheckFocusStatusQry.Request, CheckFocusStatusQry.Response> {

    override fun exec(request: CheckFocusStatusQry.Request): CheckFocusStatusQry.Response {
        return CheckFocusStatusQry.Response(
            haveFocus = TODO("set haveFocus")
        )
    }
}
