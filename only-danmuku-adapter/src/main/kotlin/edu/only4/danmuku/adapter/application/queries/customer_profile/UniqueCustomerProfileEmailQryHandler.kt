package edu.only4.danmuku.adapter.application.queries.customer_profile

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.customer_profile.UniqueCustomerProfileEmailQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class UniqueCustomerProfileEmailQryHandler : Query<UniqueCustomerProfileEmailQry.Request, UniqueCustomerProfileEmailQry.Response> {

    override fun exec(request: UniqueCustomerProfileEmailQry.Request): UniqueCustomerProfileEmailQry.Response {
        return UniqueCustomerProfileEmailQry.Response(
            exists = TODO("set exists")
        )
    }
}
