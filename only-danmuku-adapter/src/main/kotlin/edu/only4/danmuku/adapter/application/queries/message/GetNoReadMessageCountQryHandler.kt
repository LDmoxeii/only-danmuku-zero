package edu.only4.danmuku.adapter.application.queries.message

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.message.GetNoReadMessageCountQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetNoReadMessageCountQryHandler : Query<GetNoReadMessageCountQry.Request, GetNoReadMessageCountQry.Response> {

    override fun exec(request: GetNoReadMessageCountQry.Request): GetNoReadMessageCountQry.Response {
        return GetNoReadMessageCountQry.Response(
            count = TODO("set count")
        )
    }
}
