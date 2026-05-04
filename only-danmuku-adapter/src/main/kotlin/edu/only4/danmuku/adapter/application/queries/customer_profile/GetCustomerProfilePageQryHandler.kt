package edu.only4.danmuku.adapter.application.queries.customer_profile

import com.only4.cap4k.ddd.core.application.query.Query
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.application.queries._share.model.CustomerProfile
import edu.only4.danmuku.application.queries._share.model.fetchBy
import edu.only4.danmuku.application.queries._share.model.joinTime
import edu.only4.danmuku.application.queries._share.model.nickName
import edu.only4.danmuku.application.queries._share.model.status
import edu.only4.danmuku.application.queries._share.model.user
import edu.only4.danmuku.application.queries.customer_profile.GetCustomerProfilePageQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.`eq?`
import org.babyfish.jimmer.sql.kt.ast.expression.`ilike?`
import org.springframework.stereotype.Service

/**
 * 获取客户档案分页
 */
@Service
class GetCustomerProfilePageQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetCustomerProfilePageQry.Request, GetCustomerProfilePageQry.Response> {

    override fun exec(request: GetCustomerProfilePageQry.Request): GetCustomerProfilePageQry.Response {
        val pageResult = sqlClient.createQuery(CustomerProfile::class) {
            where(table.nickName `ilike?` request.nickNameFuzzy)
            where(table.user.status `eq?` request.status)
            orderBy(table.user.joinTime.desc())
            select(table.fetchBy {
                allScalarFields()
                user {
                    allScalarFields()
                }
            })
        }.fetchPage(request.pageNum - 1, request.pageSize)

        return GetCustomerProfilePageQry.Response(
            page = PageData.create(
                pageNum = request.pageNum,
                pageSize = request.pageSize,
                list = pageResult.rows.map { profile ->
                    GetCustomerProfilePageQry.Response.UserItem(
                        userId = profile.user.id,
                        avatar = profile.avatar,
                        nickName = profile.nickName,
                        email = profile.email,
                        birthday = profile.birthday,
                        joinTime = profile.user.joinTime,
                        lastLoginTime = profile.user.lastLoginTime,
                        sex = profile.sex,
                        lastLoginIp = profile.user.lastLoginIp,
                        personIntroduction = profile.personIntroduction,
                        currentCoinCount = profile.currentCoinCount,
                        totalCoinCount = profile.totalCoinCount,
                        status = profile.user.status
                    )
                },
                totalCount = pageResult.totalRowCount
            )
        )
    }
}
