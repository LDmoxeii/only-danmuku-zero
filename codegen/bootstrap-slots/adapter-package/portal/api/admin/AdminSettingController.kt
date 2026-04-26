package {{ basePackage }}.adapter.portal.api.admin

import com.only4.cap4k.ddd.core.Mediator
import {{ basePackage }}.adapter.portal.api.payload.admin_setting.GetSetting
import {{ basePackage }}.adapter.portal.api.payload.admin_setting.SaveSetting
import {{ basePackage }}.application.distributed.clients.system.GetSettingsCli
import {{ basePackage }}.application.distributed.clients.system.SaveSettingsCli
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/setting")
@Validated
class AdminSettingController {

    @PostMapping("/getSetting")
    fun getSetting(): GetSetting.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/saveSetting")
    fun saveSetting(@RequestBody @Validated request: SaveSetting.Request) {
        TODO("Pending controller adapter contract implementation.")
    }

}
