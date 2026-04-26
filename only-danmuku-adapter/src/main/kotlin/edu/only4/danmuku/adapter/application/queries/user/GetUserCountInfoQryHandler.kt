package edu.only4.danmuku.adapter.application.queries.user

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.user.GetUserCountInfoQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetUserCountInfoQryHandler : Query<GetUserCountInfoQry.Request, GetUserCountInfoQry.Response> {

    override fun exec(request: GetUserCountInfoQry.Request): GetUserCountInfoQry.Response {
        return GetUserCountInfoQry.Response(
            fansCount = TODO("set fansCount"),
            currentCoinCount = TODO("set currentCoinCount"),
            focusCount = TODO("set focusCount")
        )
    }
}
