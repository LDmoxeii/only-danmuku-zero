package edu.only4.danmuku.adapter.application.queries.video_transcode

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_transcode.ListVideoAbrVariantsQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class ListVideoAbrVariantsQryHandler : Query<ListVideoAbrVariantsQry.Request, ListVideoAbrVariantsQry.Response> {

    override fun exec(request: ListVideoAbrVariantsQry.Request): ListVideoAbrVariantsQry.Response {
        return ListVideoAbrVariantsQry.Response(
            qualities = TODO("set qualities"),
            variantJson = TODO("set variantJson")
        )
    }
}
