package edu.only4.danmuku.application.distributed.clients

import com.only4.cap4k.ddd.core.application.RequestParam

object CleanTempFilesCli {

    data class Request(
        val tempPaths: List<String>
    ) : RequestParam<Response>

    data object Response

}
