package edu.only4.danmuku.adapter.application.queries.video_post

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_post.UniqueVideoFilePostVariantQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class UniqueVideoFilePostVariantQryHandler : Query<UniqueVideoFilePostVariantQry.Request, UniqueVideoFilePostVariantQry.Response> {

    override fun exec(request: UniqueVideoFilePostVariantQry.Request): UniqueVideoFilePostVariantQry.Response {
        return UniqueVideoFilePostVariantQry.Response(
            exists = TODO("set exists")
        )
    }
}
