package edu.only4.danmuku.adapter.application.queries.video_transcode

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_transcode.GetVideoPostIdByFileIdQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetVideoPostIdByFileIdQryHandler : Query<GetVideoPostIdByFileIdQry.Request, GetVideoPostIdByFileIdQry.Response> {

    override fun exec(request: GetVideoPostIdByFileIdQry.Request): GetVideoPostIdByFileIdQry.Response {
        return GetVideoPostIdByFileIdQry.Response(
            filePostId = TODO("set filePostId"),
            filePath = TODO("set filePath"),
            videoPostId = TODO("set videoPostId"),
            fileIndex = TODO("set fileIndex")
        )
    }
}
