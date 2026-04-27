package {{ basePackage }}.adapter.portal.api.web

import {{ basePackage }}.adapter.portal.api.payload.u_center_statistics.GetActualTimeStatistics
import {{ basePackage }}.adapter.portal.api.payload.u_center_statistics.GetWeekStatisticsInfo
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
