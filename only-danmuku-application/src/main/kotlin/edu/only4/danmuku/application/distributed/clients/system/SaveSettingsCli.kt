package edu.only4.danmuku.application.distributed.clients.system

import com.only4.cap4k.ddd.core.application.RequestParam

object SaveSettingsCli {

    data class Request(
        val registerCoinCount: Int,
        val postVideoCoinCount: Int,
        val videoSize: Int,
        val videoPCount: Int,
        val videoCount: Int,
        val commentCount: Int,
        val danmukuCount: Int,
        val renameNicknameCoinCost: Int
    ) : RequestParam<Response>

    data object Response

}
