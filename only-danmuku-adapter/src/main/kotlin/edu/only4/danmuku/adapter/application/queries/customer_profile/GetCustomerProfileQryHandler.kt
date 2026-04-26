package edu.only4.danmuku.adapter.application.queries.customer_profile

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.customer_profile.GetCustomerProfileQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetCustomerProfileQryHandler : Query<GetCustomerProfileQry.Request, GetCustomerProfileQry.Response> {

    override fun exec(request: GetCustomerProfileQry.Request): GetCustomerProfileQry.Response {
        return GetCustomerProfileQry.Response(
            customerId = TODO("set customerId"),
            nickName = TODO("set nickName"),
            avatar = TODO("set avatar"),
            sex = TODO("set sex"),
            birthday = TODO("set birthday"),
            school = TODO("set school"),
            personIntroduction = TODO("set personIntroduction"),
            noticeInfo = TODO("set noticeInfo"),
            theme = TODO("set theme"),
            currentCoinCount = TODO("set currentCoinCount"),
            fansCount = TODO("set fansCount"),
            focusCount = TODO("set focusCount")
        )
    }
}
