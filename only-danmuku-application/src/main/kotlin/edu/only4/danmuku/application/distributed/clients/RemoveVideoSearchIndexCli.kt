package edu.only4.danmuku.application.distributed.clients

import com.only4.cap4k.ddd.core.application.RequestParam

object RemoveVideoSearchIndexCli {

    data class Request(
        val videoId: Long
    ) : RequestParam<Response>

    data object Response

}
