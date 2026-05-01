package edu.only4.danmuku.adapter.application.queries.statistics

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.statistics.GetSearchKeywordTopListQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetSearchKeywordTopListQryHandler : Query<GetSearchKeywordTopListQry.Request, GetSearchKeywordTopListQry.Response> {

    override fun exec(request: GetSearchKeywordTopListQry.Request): GetSearchKeywordTopListQry.Response {
        return GetSearchKeywordTopListQry.Response(
            keyword = TODO("set keyword")
        )
    }
}
