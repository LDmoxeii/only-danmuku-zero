package edu.only4.danmuku.adapter.application.queries.statistics

import com.only.engine.redis.misc.RedisUtils
import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.adapter.portal.api._share.constant.Constants
import edu.only4.danmuku.application.queries.statistics.GetSearchKeywordTopListQry
import org.springframework.stereotype.Service

/**
 * 获取搜索关键词排行榜
 */
@Service
class GetSearchKeywordTopListQryHandler : Query<GetSearchKeywordTopListQry.Request, GetSearchKeywordTopListQry.Response> {

    override fun exec(request: GetSearchKeywordTopListQry.Request): GetSearchKeywordTopListQry.Response {
        return GetSearchKeywordTopListQry.Response(
            items = RedisUtils.getCacheZSetRange<String>(Constants.REDIS_KEY_VIDEO_SEARCH_COUNT, 0, 9)
                .map { GetSearchKeywordTopListQry.Response.Item(it) }
        )
    }
}
