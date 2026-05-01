package edu.only4.danmuku.adapter.application.queries.video_comment

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_comment.GetCommentByIdQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetCommentByIdQryHandler : Query<GetCommentByIdQry.Request, GetCommentByIdQry.Response> {

    override fun exec(request: GetCommentByIdQry.Request): GetCommentByIdQry.Response {
        return GetCommentByIdQry.Response(
            commentId = TODO("set commentId"),
            videoId = TODO("set videoId"),
            videoOwnerId = TODO("set videoOwnerId"),
            userId = TODO("set userId"),
            parentId = TODO("set parentId"),
            content = TODO("set content")
        )
    }
}
