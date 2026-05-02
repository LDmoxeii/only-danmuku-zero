package edu.only4.danmuku.adapter.application.queries.message

import edu.only4.danmuku.adapter.support.CurrentUser

import com.only.engine.satoken.utils.LoginHelper
import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.CustomerMessage
import edu.only4.danmuku.application.queries._share.model.customerId
import edu.only4.danmuku.application.queries._share.model.messageType
import edu.only4.danmuku.application.queries._share.model.readType
import edu.only4.danmuku.application.queries.message.GetNoReadMessageCountGroupQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

/**
 * 获取未读消息数分组
 */
@Service
class GetNoReadMessageCountGroupQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetNoReadMessageCountGroupQry.Request, GetNoReadMessageCountGroupQry.Response> {

    override fun exec(request: GetNoReadMessageCountGroupQry.Request): GetNoReadMessageCountGroupQry.Response {
        val currentUserId = CurrentUser.id()
            ?: return GetNoReadMessageCountGroupQry.Response(list = emptyList())
        val types = sqlClient.createQuery(CustomerMessage::class) {
            where(table.customerId eq currentUserId)
            where(table.readType eq 1)
            select(table.messageType)
        }.execute()
        val items = types.groupingBy { it }.eachCount().entries.map { (type, count) ->
            GetNoReadMessageCountGroupQry.Response.Item(
                messageType = type,
                count = count
            )
        }

        return GetNoReadMessageCountGroupQry.Response(
            list = items
        )
    }
}
