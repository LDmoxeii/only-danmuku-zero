package edu.only4.danmuku.adapter.portal.api.admin

import cn.dev33.satoken.annotation.SaIgnore
import edu.only4.danmuku.adapter.portal.api.payload.admin_account.CheckCode
import edu.only4.danmuku.adapter.portal.api.payload.admin_account.Login
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author zhenyu.jiang
 */
@RestController
@RequestMapping("/admin/account")
class AdminAccountController {

    @SaIgnore
    @PostMapping("/checkCode")
    fun checkCode(): CheckCode.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/login")
    fun login(@RequestBody @Validated request: Login.Request): Login.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/logout")
    fun logout() {
        TODO("Pending controller adapter contract implementation.")
    }
}
