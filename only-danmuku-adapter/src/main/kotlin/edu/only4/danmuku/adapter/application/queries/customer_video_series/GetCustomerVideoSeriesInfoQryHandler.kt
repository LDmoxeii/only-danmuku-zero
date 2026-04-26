package edu.only4.danmuku.adapter.application.queries.customer_video_series

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.customer_video_series.GetCustomerVideoSeriesInfoQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetCustomerVideoSeriesInfoQryHandler : Query<GetCustomerVideoSeriesInfoQry.Request, GetCustomerVideoSeriesInfoQry.Response> {

    override fun exec(request: GetCustomerVideoSeriesInfoQry.Request): GetCustomerVideoSeriesInfoQry.Response {
        return GetCustomerVideoSeriesInfoQry.Response(
            seriesId = TODO("set seriesId"),
            userId = TODO("set userId"),
            seriesName = TODO("set seriesName"),
            seriesDescription = TODO("set seriesDescription"),
            sort = TODO("set sort"),
            createTime = TODO("set createTime"),
            updateTime = TODO("set updateTime"),
            videoList = TODO("set videoList")
        )
    }
}
