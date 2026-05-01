package edu.only4.danmuku.adapter.portal.api.web

import cn.dev33.satoken.annotation.SaIgnore
import edu.only4.danmuku.adapter.portal.api.payload.setting.GetSetting
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
