package edu.only4.danmuku.adapter.portal.api.payload.account

object AccountLogin {

    data class Request(
        val email: String,
        val password: String,
        val checkCodeKey: String,
        val checkCode: String
    )

    class Response

}
