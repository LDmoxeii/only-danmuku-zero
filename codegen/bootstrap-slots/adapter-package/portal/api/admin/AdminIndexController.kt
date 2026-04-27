package {{ basePackage }}.adapter.portal.api.admin

import {{ basePackage }}.adapter.portal.api.payload.admin_index.GetActualTimeStatistics
import {{ basePackage }}.adapter.portal.api.payload.admin_index.GetWeekStatisticsInfo
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 管理员统计信息控制器
 */
@RestController
@RequestMapping("/admin/index")
class AdminIndexController {

    @PostMapping("/getActualTimeStatisticsInfo")
    fun getActualTimeStatistics(): GetActualTimeStatistics.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/getWeekStatisticsInfo")
    fun getWeekStatisticsInfo(@RequestBody @Validated request: GetWeekStatisticsInfo.Request): List<GetWeekStatisticsInfo.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

}
