package edu.only4.danmuku.adapter.application.queries.statistics

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.statistics.GetTotalStatisticsInfoQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetTotalStatisticsInfoQryHandler : Query<GetTotalStatisticsInfoQry.Request, GetTotalStatisticsInfoQry.Response> {

    override fun exec(request: GetTotalStatisticsInfoQry.Request): GetTotalStatisticsInfoQry.Response {
        return GetTotalStatisticsInfoQry.Response(
            userCount = TODO("set userCount"),
            playCount = TODO("set playCount"),
            commentCount = TODO("set commentCount"),
            danmukuCount = TODO("set danmukuCount"),
            likeCount = TODO("set likeCount"),
            collectCount = TODO("set collectCount"),
            coinCount = TODO("set coinCount")
        )
    }
}
