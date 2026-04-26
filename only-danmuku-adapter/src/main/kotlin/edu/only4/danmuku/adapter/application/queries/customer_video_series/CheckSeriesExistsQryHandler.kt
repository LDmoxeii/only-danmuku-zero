package edu.only4.danmuku.adapter.application.queries.customer_video_series

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.customer_video_series.CheckSeriesExistsQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class CheckSeriesExistsQryHandler : Query<CheckSeriesExistsQry.Request, CheckSeriesExistsQry.Response> {

    override fun exec(request: CheckSeriesExistsQry.Request): CheckSeriesExistsQry.Response {
        return CheckSeriesExistsQry.Response(
            exists = TODO("set exists")
        )
    }
}
