package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video.GetVideoPlayFilesQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetVideoPlayFilesQryHandler : Query<GetVideoPlayFilesQry.Request, GetVideoPlayFilesQry.Response> {

    override fun exec(request: GetVideoPlayFilesQry.Request): GetVideoPlayFilesQry.Response {
        return GetVideoPlayFilesQry.Response(
            fileId = TODO("set fileId"),
            videoId = TODO("set videoId"),
            fileIndex = TODO("set fileIndex"),
            fileName = TODO("set fileName"),
            fileSize = TODO("set fileSize"),
            filePath = TODO("set filePath"),
            duration = TODO("set duration")
        )
    }
}
