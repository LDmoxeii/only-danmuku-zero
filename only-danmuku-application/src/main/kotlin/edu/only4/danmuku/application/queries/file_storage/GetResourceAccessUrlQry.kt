package edu.only4.danmuku.application.queries.file_storage

import com.only4.cap4k.ddd.core.application.RequestParam

object GetResourceAccessUrlQry {

    data class Request(
        val resourceKey: String,
        val preferPresign: Boolean = false,
        val expireSeconds: Int = 600
    ) : RequestParam<Response>

    data class Response(
        val url: String
    )

}
