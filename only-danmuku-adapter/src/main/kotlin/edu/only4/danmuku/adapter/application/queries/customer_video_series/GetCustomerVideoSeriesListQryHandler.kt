package edu.only4.danmuku.adapter.application.queries.customer_video_series

import com.only4.cap4k.ddd.core.application.query.ListQuery
import edu.only4.danmuku.application.queries.customer_video_series.GetCustomerVideoSeriesListQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetCustomerVideoSeriesListQryHandler : ListQuery<GetCustomerVideoSeriesListQry.Request, GetCustomerVideoSeriesListQry.Response> {

    override fun exec(request: GetCustomerVideoSeriesListQry.Request): List<GetCustomerVideoSeriesListQry.Response> {
        return listOf(
            GetCustomerVideoSeriesListQry.Response(
                seriesId = TODO("set seriesId"),
                seriesName = TODO("set seriesName"),
                seriesDescription = TODO("set seriesDescription"),
                sort = TODO("set sort"),
                videoCount = TODO("set videoCount"),
                cover = TODO("set cover"),
                createTime = TODO("set createTime"),
                updateTime = TODO("set updateTime")
            )
        )
    }
}
