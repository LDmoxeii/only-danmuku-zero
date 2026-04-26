package edu.only4.danmuku.adapter.portal.api.payload.admin_video

object GetVideoPlist {

    data class Request(
        val videoId: Long
    )

    data class Response(
        val fileId: String?,
        val videoId: String?,
        val fileIndex: Int?,
        val fileName: String?,
        val fileSize: Long?,
        val filePath: String?,
        val duration: Int?
    )

}
