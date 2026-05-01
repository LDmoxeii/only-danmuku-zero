package edu.only4.danmuku.adapter.application.queries.video_file

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_file.GetVideoFilesByVideoIdQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetVideoFilesByVideoIdQryHandler : Query<GetVideoFilesByVideoIdQry.Request, GetVideoFilesByVideoIdQry.Response> {

    override fun exec(request: GetVideoFilesByVideoIdQry.Request): GetVideoFilesByVideoIdQry.Response {
        return GetVideoFilesByVideoIdQry.Response(
            fileId = TODO("set fileId"),
            videoId = TODO("set videoId"),
            userId = TODO("set userId"),
            fileIndex = TODO("set fileIndex"),
            fileName = TODO("set fileName"),
            fileSize = TODO("set fileSize"),
            filePath = TODO("set filePath"),
            duration = TODO("set duration")
        )
    }
}
