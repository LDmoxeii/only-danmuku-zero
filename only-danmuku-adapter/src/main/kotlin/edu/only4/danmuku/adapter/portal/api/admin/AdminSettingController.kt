package edu.only4.danmuku.adapter.portal.api.admin

import edu.only4.danmuku.adapter.portal.api.payload.admin_setting.GetSetting
import edu.only4.danmuku.adapter.portal.api.payload.admin_setting.SaveSetting
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
