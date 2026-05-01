package edu.only4.danmuku.adapter.application.queries.customer_profile

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.customer_profile.CheckUserCoinBalanceQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class CheckUserCoinBalanceQryHandler : Query<CheckUserCoinBalanceQry.Request, CheckUserCoinBalanceQry.Response> {

    override fun exec(request: CheckUserCoinBalanceQry.Request): CheckUserCoinBalanceQry.Response {
        return CheckUserCoinBalanceQry.Response(
            sufficient = TODO("set sufficient"),
            currentBalance = TODO("set currentBalance")
        )
    }
}
