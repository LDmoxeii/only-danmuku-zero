package edu.only4.danmuku.adapter.application.queries.message

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.message.GetNoReadMessageCountGroupQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetNoReadMessageCountGroupQryHandler : Query<GetNoReadMessageCountGroupQry.Request, GetNoReadMessageCountGroupQry.Response> {

    override fun exec(request: GetNoReadMessageCountGroupQry.Request): GetNoReadMessageCountGroupQry.Response {
        return GetNoReadMessageCountGroupQry.Response(
            list = TODO("set list")
        )
    }
}
