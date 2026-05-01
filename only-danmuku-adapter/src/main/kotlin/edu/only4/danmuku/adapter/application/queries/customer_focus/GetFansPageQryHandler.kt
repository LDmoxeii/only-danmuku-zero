package edu.only4.danmuku.adapter.application.queries.customer_focus

import com.only4.cap4k.ddd.core.application.query.Query
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.application.queries._share.model.CustomerFocus
import edu.only4.danmuku.application.queries._share.model.customerId
import edu.only4.danmuku.application.queries._share.model.fetchBy
import edu.only4.danmuku.application.queries._share.model.focusCustomerId
import edu.only4.danmuku.application.queries.customer_focus.GetFansPageQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.count
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

/**
 * 获取粉丝列表
 */
@Service
class GetFansPageQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetFansPageQry.Request, GetFansPageQry.Response> {

    override fun exec(request: GetFansPageQry.Request): GetFansPageQry.Response {
        val pageResult = sqlClient.createQuery(CustomerFocus::class) {
            where(table.focusCustomerId eq request.userId)
            select(table.fetchBy {
                customerId()
                customer {
                    relation {
                        nickName()
                        avatar()
                        personIntroduction()
                    }
                }
                focusCustomerId()
            })
        }.fetchPage(request.pageNum - 1, request.pageSize)
        val myFocusUserIds = sqlClient.createQuery(CustomerFocus::class) {
            where(table.customerId eq request.userId)
            select(table.focusCustomerId)
        }.execute().toSet()

        return GetFansPageQry.Response(
            page = PageData.create(
                pageNum = request.pageNum,
                pageSize = request.pageSize,
                list = pageResult.rows.map { focus ->
                    val fansCount = sqlClient.createQuery(CustomerFocus::class) {
                        where(table.focusCustomerId eq focus.customerId)
                        select(count(table))
                    }.fetchOne().toInt()
                    GetFansPageQry.Response.FansItem(
                        userId = focus.customerId,
                        nickName = focus.customer.relation!!.nickName,
                        avatar = focus.customer.relation!!.avatar,
                        personIntroduction = focus.customer.relation!!.personIntroduction,
                        fansCount = fansCount,
                        haveFocus = myFocusUserIds.contains(focus.customerId),
                        focusType = if (myFocusUserIds.contains(focus.customerId)) 1 else 0
                    )
                },
                totalCount = pageResult.totalRowCount
            )
        )
    }
}
