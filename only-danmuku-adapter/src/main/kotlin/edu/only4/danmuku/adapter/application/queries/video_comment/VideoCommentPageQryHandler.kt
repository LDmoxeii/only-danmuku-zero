package edu.only4.danmuku.adapter.application.queries.video_comment

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_comment.VideoCommentPageQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class VideoCommentPageQryHandler : Query<VideoCommentPageQry.Request, VideoCommentPageQry.Response> {

    override fun exec(request: VideoCommentPageQry.Request): VideoCommentPageQry.Response {
        return VideoCommentPageQry.Response(
            page = TODO("set page")
        )
    }
}
