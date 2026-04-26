package edu.only4.danmuku.adapter.application.queries.customer_video_series

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.customer_video_series.GetCustomerVideoSeriesVideoQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetCustomerVideoSeriesVideoQryHandler : Query<GetCustomerVideoSeriesVideoQry.Request, GetCustomerVideoSeriesVideoQry.Response> {

    override fun exec(request: GetCustomerVideoSeriesVideoQry.Request): GetCustomerVideoSeriesVideoQry.Response {
        return GetCustomerVideoSeriesVideoQry.Response(
            seriesId = TODO("set seriesId"),
            seriesName = TODO("set seriesName"),
            seriesDescription = TODO("set seriesDescription"),
            sort = TODO("set sort"),
            videoList = TODO("set videoList")
        )
    }
}
