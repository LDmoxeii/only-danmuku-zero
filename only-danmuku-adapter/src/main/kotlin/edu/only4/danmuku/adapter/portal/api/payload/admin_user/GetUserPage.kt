
package edu.only4.danmuku.adapter.portal.api.payload.admin_user

object GetUserPage {

    data class Request(
        val nickNameFuzzy: String?,
        val status: Int?
    )

    data class Response(
        val userId: Long,
        val avatar: String?,
        val nickName: String?,
        val email: String?,
        val birthday: String?,
        val joinTime: Long,
        val lastLoginTime: Long?,
        val sex: Int?,
        val lastLoginIp: String?,
        val personIntroduction: String?,
        val currentCoinCount: Int?,
        val totalCoinCount: Int?,
        val status: Int?
    )

}
