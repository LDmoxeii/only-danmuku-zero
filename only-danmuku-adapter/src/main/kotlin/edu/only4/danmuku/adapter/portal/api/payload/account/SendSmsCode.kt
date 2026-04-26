package edu.only4.danmuku.adapter.portal.api.payload.account

object SendSmsCode {

    data class Request(
        val scene: String,
        val phone: String
    )

    class Response

}
