package {{ basePackage }}.adapter.portal.api.web

import cn.dev33.satoken.annotation.SaIgnore
import com.only4.cap4k.ddd.core.Mediator
import {{ basePackage }}.adapter.portal.api.payload.setting.GetSetting
import {{ basePackage }}.application.distributed.clients.system.GetSettingsCli
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SaIgnore
@RestController
@RequestMapping("/sysSetting")
class SettingController {

    @PostMapping("/getSetting")
    fun getSetting(): GetSetting.Response {
        TODO("Pending controller adapter contract implementation.")
    }
}
