package edu.only4.danmuku.adapter.application.queries.video_comment

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_comment.CommentExistsByIdQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class CommentExistsByIdQryHandler : Query<CommentExistsByIdQry.Request, CommentExistsByIdQry.Response> {

    override fun exec(request: CommentExistsByIdQry.Request): CommentExistsByIdQry.Response {
        return CommentExistsByIdQry.Response(
            exists = TODO("set exists")
        )
    }
}
