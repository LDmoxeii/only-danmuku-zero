
package edu.only4.danmuku.adapter.portal.api.payload.admin_account

object Login {

    data class Request(
        val account: String = "",
        val password: String = "",
        val checkCode: String = "",
        val checkCodeKey: String = ""
    )

    class Response

}
