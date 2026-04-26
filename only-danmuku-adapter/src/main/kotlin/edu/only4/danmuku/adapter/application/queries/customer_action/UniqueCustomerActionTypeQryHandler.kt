package edu.only4.danmuku.adapter.application.queries.customer_action

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.customer_action.UniqueCustomerActionTypeQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class UniqueCustomerActionTypeQryHandler : Query<UniqueCustomerActionTypeQry.Request, UniqueCustomerActionTypeQry.Response> {

    override fun exec(request: UniqueCustomerActionTypeQry.Request): UniqueCustomerActionTypeQry.Response {
        return UniqueCustomerActionTypeQry.Response(
            exists = TODO("set exists")
        )
    }
}
