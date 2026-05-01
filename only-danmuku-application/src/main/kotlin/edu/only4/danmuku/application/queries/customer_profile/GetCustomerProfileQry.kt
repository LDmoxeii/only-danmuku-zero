
package edu.only4.danmuku.application.queries.customer_profile

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.danmuku.domain.aggregates.customer_profile.enums.ThemeType

object GetCustomerProfileQry {

    data class Request(
        val customerId: Long
    ) : RequestParam<Response>

    data class Response(
        val customerId: Long,
        val nickName: String,
        val avatar: String?,
        val sex: Int,
        val birthday: String?,
        val school: String?,
        val personIntroduction: String?,
        val noticeInfo: String?,
        val theme: ThemeType,
        val currentCoinCount: Int,
        val fansCount: Long = 0L,
        val focusCount: Long = 0L
    )

}
