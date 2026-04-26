package edu.only4.danmuku.adapter.portal.api.payload.u_home

object BindPhone {

    data class Request(
        val phone: String,
        val smsCode: String,
        val captchaId: String
    )

    class Response

}
