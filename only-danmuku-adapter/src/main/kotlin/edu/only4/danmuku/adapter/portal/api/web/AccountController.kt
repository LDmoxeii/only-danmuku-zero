package edu.only4.danmuku.adapter.portal.api.web

import cn.dev33.satoken.annotation.SaIgnore
import edu.only4.danmuku.adapter.portal.api.payload.account.*
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class AccountController {

    @SaIgnore
    @PostMapping("/checkCode")
    fun checkCode(): CheckCode.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/register")
    fun register(@RequestBody @Validated request: AccountRegister.Request) {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/login")
    fun login(@RequestBody @Validated request: AccountLogin.Request): AccountLogin.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/autoLogin")
    fun autoLogin() : AccountLogin.Response? {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/logout")
    fun logout() {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/getUserCountInfo")
    fun getUserCountInfo(): GetUserCountInfo.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/sendSmsCode")
    fun sendSmsCode(@RequestBody @Validated request: SendSmsCode.Request): SendSmsCode.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/loginBySms")
    fun loginBySms(@RequestBody @Validated request: LoginBySms.Request): LoginBySms.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/changePassword")
    fun changePassword(@RequestBody @Validated request: ChangePassword.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
}
