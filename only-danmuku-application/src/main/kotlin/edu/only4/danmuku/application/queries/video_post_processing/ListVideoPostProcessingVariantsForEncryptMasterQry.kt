
package edu.only4.danmuku.application.queries.video_post_processing

import com.only4.cap4k.ddd.core.application.RequestParam

object ListVideoPostProcessingVariantsForEncryptMasterQry {

    data class Request(
        val videoPostId: Long,
        val fileIndex: Int
    ) : RequestParam<Response>

    data class Response(
        val items: List<VariantItem>
    ) {
        data class VariantItem(
            val quality: String,
            val width: Int,
            val height: Int,
            val bandwidthBps: Int,
            val playlistPath: String
        )
    }

}
