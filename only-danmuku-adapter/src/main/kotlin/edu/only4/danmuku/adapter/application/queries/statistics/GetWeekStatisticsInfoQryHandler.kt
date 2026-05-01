package edu.only4.danmuku.adapter.application.queries.statistics

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.Statistics
import edu.only4.danmuku.application.queries._share.model.customerId
import edu.only4.danmuku.application.queries._share.model.dataType
import edu.only4.danmuku.application.queries._share.model.statisticsCount
import edu.only4.danmuku.application.queries._share.model.statisticsDate
import edu.only4.danmuku.application.queries.statistics.GetWeekStatisticsInfoQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.`eq?`
import org.babyfish.jimmer.sql.kt.ast.expression.ge
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneId

/**
 * 获取最近七天的统计数据
 */
@Service
class GetWeekStatisticsInfoQryHandler(
    private val sqlClient: KSqlClient
) : Query<GetWeekStatisticsInfoQry.Request, GetWeekStatisticsInfoQry.Response> {

    override fun exec(request: GetWeekStatisticsInfoQry.Request): GetWeekStatisticsInfoQry.Response {
        val sevenDaysAgoStartOfDay = LocalDate.now().minusDays(6)
            .atStartOfDay(ZoneId.systemDefault())
            .toEpochSecond()
        val statisticsList = sqlClient.createQuery(Statistics::class) {
            where(table.statisticsDate ge sevenDaysAgoStartOfDay)
            where(table.dataType eq request.dataType)
            where(table.customerId `eq?` request.userId)
            select(table.statisticsDate, table.statisticsCount)
        }.execute()
        val groupedByDate = statisticsList
            .groupBy { it._1 }
            .mapValues { (_, items) -> items.sumOf { it._2 ?: 0 } }

        return GetWeekStatisticsInfoQry.Response(
            items = groupedByDate
                .map { (date, count) ->
                    GetWeekStatisticsInfoQry.Response.StatisticsItem(
                        date = date,
                        count = count
                    )
                }
                .sortedBy { it.date }
        )
    }
}
