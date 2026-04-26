package edu.only4.danmuku.adapter.portal.api.payload.account

object ChangePassword {

    data class Request(
        val oldPassword: String,
        val newPassword: String
    )

    class Response

}
