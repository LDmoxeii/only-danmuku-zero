package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video.ListVideoFileVariantsQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class ListVideoFileVariantsQryHandler : Query<ListVideoFileVariantsQry.Request, ListVideoFileVariantsQry.Response> {

    override fun exec(request: ListVideoFileVariantsQry.Request): ListVideoFileVariantsQry.Response {
        return ListVideoFileVariantsQry.Response(
            qualities = TODO("set qualities"),
            variantJson = TODO("set variantJson")
        )
    }
}
