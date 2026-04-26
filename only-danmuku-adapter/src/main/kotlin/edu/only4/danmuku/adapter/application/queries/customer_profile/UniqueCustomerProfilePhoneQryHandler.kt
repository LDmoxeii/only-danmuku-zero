package edu.only4.danmuku.adapter.application.queries.customer_profile

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.customer_profile.UniqueCustomerProfilePhoneQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class UniqueCustomerProfilePhoneQryHandler : Query<UniqueCustomerProfilePhoneQry.Request, UniqueCustomerProfilePhoneQry.Response> {

    override fun exec(request: UniqueCustomerProfilePhoneQry.Request): UniqueCustomerProfilePhoneQry.Response {
        return UniqueCustomerProfilePhoneQry.Response(
            exists = TODO("set exists")
        )
    }
}
