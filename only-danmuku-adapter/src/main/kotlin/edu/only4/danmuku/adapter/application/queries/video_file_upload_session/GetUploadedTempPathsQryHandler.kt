package edu.only4.danmuku.adapter.application.queries.video_file_upload_session

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_file_upload_session.GetUploadedTempPathsQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetUploadedTempPathsQryHandler : Query<GetUploadedTempPathsQry.Request, GetUploadedTempPathsQry.Response> {

    override fun exec(request: GetUploadedTempPathsQry.Request): GetUploadedTempPathsQry.Response {
        return GetUploadedTempPathsQry.Response
    }
}
