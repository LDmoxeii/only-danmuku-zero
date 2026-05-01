package edu.only4.danmuku.application.distributed.clients.oss

import com.only4.cap4k.ddd.core.application.RequestParam

object ReadObjectAsTextCli {

    data class Request(
        val objectKey: String
    ) : RequestParam<Response>

    data class Response(
        val content: String
    )

}
