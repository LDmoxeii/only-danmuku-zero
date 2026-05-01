package edu.only4.danmuku.adapter.application.queries.customer_video_series

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.customer_video_series.CheckSeriesNameExistsQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class CheckSeriesNameExistsQryHandler : Query<CheckSeriesNameExistsQry.Request, CheckSeriesNameExistsQry.Response> {

    override fun exec(request: CheckSeriesNameExistsQry.Request): CheckSeriesNameExistsQry.Response {
        return CheckSeriesNameExistsQry.Response(
            exists = TODO("set exists")
        )
    }
}
