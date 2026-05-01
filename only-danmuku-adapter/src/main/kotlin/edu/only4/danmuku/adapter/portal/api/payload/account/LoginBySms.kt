
package edu.only4.danmuku.adapter.portal.api.payload.account

object LoginBySms {

    data class Request(
        val phone: String,
        val smsCode: String,
        val captchaId: String
    )

    class Response

}
