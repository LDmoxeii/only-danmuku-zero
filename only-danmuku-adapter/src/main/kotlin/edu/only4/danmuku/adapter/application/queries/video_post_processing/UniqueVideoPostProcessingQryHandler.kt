package edu.only4.danmuku.adapter.application.queries.video_post_processing

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_post_processing.UniqueVideoPostProcessingQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class UniqueVideoPostProcessingQryHandler : Query<UniqueVideoPostProcessingQry.Request, UniqueVideoPostProcessingQry.Response> {

    override fun exec(request: UniqueVideoPostProcessingQry.Request): UniqueVideoPostProcessingQry.Response {
        return UniqueVideoPostProcessingQry.Response(
            exists = TODO("set exists")
        )
    }
}
