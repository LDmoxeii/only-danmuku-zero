package edu.only4.danmuku.adapter.application.queries.video_transcode

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_transcode.GetUploadSessionTempPathQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetUploadSessionTempPathQryHandler : Query<GetUploadSessionTempPathQry.Request, GetUploadSessionTempPathQry.Response> {

    override fun exec(request: GetUploadSessionTempPathQry.Request): GetUploadSessionTempPathQry.Response {
        return GetUploadSessionTempPathQry.Response(
            tempPath = TODO("set tempPath")
        )
    }
}
