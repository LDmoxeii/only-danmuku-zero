package edu.only4.danmuku.adapter.portal.api.payload.account

object AccountRegister {

    data class Request(
        val email: String,
        val nickName: String,
        val registerPassword: String,
        val checkCodeKey: String,
        val checkCode: String
    )

    class Response

}
