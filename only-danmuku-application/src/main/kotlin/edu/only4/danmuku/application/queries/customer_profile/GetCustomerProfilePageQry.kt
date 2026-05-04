
package edu.only4.danmuku.application.queries.customer_profile

import java.util.UUID

import com.only4.cap4k.ddd.core.application.RequestParam
import com.only4.cap4k.ddd.core.application.query.PageRequest
import com.only4.cap4k.ddd.core.share.PageData

object GetCustomerProfilePageQry {

    data class Request(
        override var pageNum: Int = 1,
        override var pageSize: Int = 10,
        var nickNameFuzzy: String? = null,
        var status: Int? = null
    ) : PageRequest, RequestParam<Response>

    data class Response(
        val page: PageData<UserItem>
    ) {
        data class UserItem(
            var userId: UUID,
            var avatar: String?,
            var nickName: String?,
            var email: String?,
            var birthday: String?,
            var joinTime: Long,
            var lastLoginTime: Long?,
            var sex: Int?,
            var lastLoginIp: String?,
            var personIntroduction: String?,
            var currentCoinCount: Int?,
            var totalCoinCount: Int?,
            var status: Int?
        )
    }

}

