package edu.only4.danmuku.application.queries.video_draft

import com.only4.cap4k.ddd.core.application.RequestParam
import edu.only4.danmuku.domain.aggregates.video_post.enums.PostType
import edu.only4.danmuku.domain.aggregates.video_post.enums.TransferResult
import edu.only4.danmuku.domain.aggregates.video_post.enums.VideoStatus

object GetVideoPostInfoQry {

    data class Request(
        val videoPostId: Long,
        val userId: Long
    ) : RequestParam<Response>

    data class Response(
        val videoInfo: VideoInfo,
        val videoFileList: List<VideoFileList>
    ) {
        data class VideoInfo(
            val videoId: Long,
            val videoCover: String?,
            val videoName: String?,
            val parentCategoryId: Long?,
            val categoryId: Long?,
            val postType: PostType?,
            val originInfo: String?,
            val tags: String?,
            val introduction: String?,
            val interaction: String?,
            val status: VideoStatus
        )
        data class VideoFileList(
            val fileId: Long,
            val uploadId: String,
            val fileIndex: Int,
            val fileName: String,
            val fileSize: Long,
            val filePath: String?,
            val duration: Int,
            val transferResult: TransferResult
        )
    }

}
