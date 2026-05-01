
package edu.only4.danmuku.application.queries.video_post_processing

import com.only4.cap4k.ddd.core.application.RequestParam

object ListVideoPostProcessingFilesForSyncQry {

    data class Request(
        val videoPostId: Long
    ) : RequestParam<Response>

    data class Response(
        val fileIndex: Int,
        val transcodeOutputPrefix: String?,
        val encryptOutputPrefix: String?,
        val variants: List<VariantItem>,
        val duration: Int?,
        val fileSize: Long?,
        val encryptMethod: String?,
        val keyVersion: Int?
    ) {
        data class VariantItem(
            val quality: String,
            val width: Int,
            val height: Int,
            val videoBitrateKbps: Int,
            val audioBitrateKbps: Int,
            val bandwidthBps: Int,
            val playlistPath: String,
            val segmentPrefix: String?,
            val segmentDuration: Int?
        )
    }

}
