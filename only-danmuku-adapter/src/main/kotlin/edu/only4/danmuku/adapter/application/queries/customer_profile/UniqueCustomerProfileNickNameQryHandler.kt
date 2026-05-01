package edu.only4.danmuku.adapter.application.queries.customer_profile

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.customer_profile.UniqueCustomerProfileNickNameQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class UniqueCustomerProfileNickNameQryHandler : Query<UniqueCustomerProfileNickNameQry.Request, UniqueCustomerProfileNickNameQry.Response> {

    override fun exec(request: UniqueCustomerProfileNickNameQry.Request): UniqueCustomerProfileNickNameQry.Response {
        return UniqueCustomerProfileNickNameQry.Response(
            exists = TODO("set exists")
        )
    }
}
