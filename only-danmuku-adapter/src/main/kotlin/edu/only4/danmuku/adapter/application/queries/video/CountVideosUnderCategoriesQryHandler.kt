package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video.CountVideosUnderCategoriesQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class CountVideosUnderCategoriesQryHandler : Query<CountVideosUnderCategoriesQry.Request, CountVideosUnderCategoriesQry.Response> {

    override fun exec(request: CountVideosUnderCategoriesQry.Request): CountVideosUnderCategoriesQry.Response {
        return CountVideosUnderCategoriesQry.Response(
            totalCount = TODO("set totalCount")
        )
    }
}
