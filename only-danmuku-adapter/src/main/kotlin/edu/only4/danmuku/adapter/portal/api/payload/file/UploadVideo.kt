package edu.only4.danmuku.adapter.portal.api.payload.file

object UploadVideo {

    data class Request(
        val chunkIndex: Int = 0,
        val uploadId: String
    )

    class Response

}
