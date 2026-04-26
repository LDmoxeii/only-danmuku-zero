package {{ basePackage }}.adapter.portal.api.web

import com.only.engine.satoken.utils.LoginHelper
import com.only4.cap4k.ddd.core.Mediator
import {{ basePackage }}.adapter.portal.api.payload.u_center_statistics.GetActualTimeStatistics
import {{ basePackage }}.adapter.portal.api.payload.u_center_statistics.GetWeekStatisticsInfo
import {{ basePackage }}.application.queries.statistics.GetPreviousDayStatisticsInfoQry
import {{ basePackage }}.application.queries.statistics.GetTotalStatisticsInfoQry
import {{ basePackage }}.application.queries.statistics.GetWeekStatisticsInfoQry
import {{ basePackage }}.domain.aggregates.statistics.enums.StatisticsDataType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/uCenter")
class UCenterStatisticsController {

    @PostMapping("/getActualTimeStatistics")
    fun getActualTimeStatistics(): GetActualTimeStatistics.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/getWeekStatisticsInfo")
    fun getWeekStatisticsInfo(@RequestBody @Validated request: GetWeekStatisticsInfo.Request): List<GetWeekStatisticsInfo.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

}
