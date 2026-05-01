
package edu.only4.danmuku.adapter.portal.api.payload.admin_setting

object SaveSetting {

    data class Request(
        val registerCoinCount: Int,
        val postVideoCoinCount: Int,
        val videoSize: Int,
        val videoPCount: Int,
        val videoCount: Int,
        val commentCount: Int,
        val danmukuCount: Int
    )

    class Response

}
