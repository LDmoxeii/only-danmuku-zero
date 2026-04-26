package edu.only4.danmuku.adapter.application.queries.video_transcode

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_transcode.GetVideoAbrMasterQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetVideoAbrMasterQryHandler : Query<GetVideoAbrMasterQry.Request, GetVideoAbrMasterQry.Response> {

    override fun exec(request: GetVideoAbrMasterQry.Request): GetVideoAbrMasterQry.Response {
        return GetVideoAbrMasterQry.Response(
            status = TODO("set status"),
            masterPath = TODO("set masterPath")
        )
    }
}
