
package edu.only4.danmuku.adapter.portal.api.payload.u_home

object UpdateCustomerProfile {

    data class Request(
        val nickName: String,
        val avatar: String,
        val sex: Int,
        val birthday: String?,
        val school: String?,
        val personIntroduction: String?,
        val noticeInfo: String?
    )

    class Response

}
