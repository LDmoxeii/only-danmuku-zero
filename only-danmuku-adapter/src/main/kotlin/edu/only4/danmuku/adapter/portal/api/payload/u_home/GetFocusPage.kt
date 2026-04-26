package edu.only4.danmuku.adapter.portal.api.payload.u_home

object GetFocusPage {

    class Request

    data class Response(
        val otherUserId: String?,
        val otherNickName: String?,
        val otherPersonIntroduction: String?,
        val otherAvatar: String?,
        val focusType: Int?
    )

}
