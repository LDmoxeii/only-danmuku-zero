package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video.UniqueVideoFileVariantQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class UniqueVideoFileVariantQryHandler : Query<UniqueVideoFileVariantQry.Request, UniqueVideoFileVariantQry.Response> {

    override fun exec(request: UniqueVideoFileVariantQry.Request): UniqueVideoFileVariantQry.Response {
        return UniqueVideoFileVariantQry.Response(
            exists = TODO("set exists")
        )
    }
}
