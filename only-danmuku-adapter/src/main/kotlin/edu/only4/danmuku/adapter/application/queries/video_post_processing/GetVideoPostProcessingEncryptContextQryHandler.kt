package edu.only4.danmuku.adapter.application.queries.video_post_processing

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_post_processing.GetVideoPostProcessingEncryptContextQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetVideoPostProcessingEncryptContextQryHandler : Query<GetVideoPostProcessingEncryptContextQry.Request, GetVideoPostProcessingEncryptContextQry.Response> {

    override fun exec(request: GetVideoPostProcessingEncryptContextQry.Request): GetVideoPostProcessingEncryptContextQry.Response {
        return GetVideoPostProcessingEncryptContextQry.Response(
            transcodeOutputPrefix = TODO("set transcodeOutputPrefix"),
            encryptOutputDir = TODO("set encryptOutputDir")
        )
    }
}
