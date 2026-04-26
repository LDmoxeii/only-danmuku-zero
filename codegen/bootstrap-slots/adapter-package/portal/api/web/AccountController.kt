package {{ basePackage }}.adapter.portal.api.web

import cn.dev33.satoken.annotation.SaIgnore
import cn.dev33.satoken.stp.StpUtil
import com.only.engine.enums.CaptchaChannel
import com.only.engine.exception.BusinessException
import com.only.engine.misc.ServletUtils
import {{ basePackage }}.domain.shared.error.DanmukuBusinessErrors
import {{ basePackage }}.domain.shared.error.DanmukuAuthErrors
import com.only.engine.misc.ServletUtils.getClientIP
import com.only.engine.satoken.utils.LoginHelper
import com.only4.cap4k.ddd.core.Mediator
import {{ basePackage }}.adapter.portal.api.payload.account.CheckCode
import {{ basePackage }}.adapter.portal.api.payload.account.AccountLogin
import {{ basePackage }}.adapter.portal.api.payload.account.AccountRegister
import {{ basePackage }}.adapter.portal.api.payload.account.GetUserCountInfo
import {{ basePackage }}.adapter.portal.api.payload.account.ChangePassword
import {{ basePackage }}.adapter.portal.api.payload.account.LoginBySms
import {{ basePackage }}.adapter.portal.api.payload.account.SendSmsCode
import {{ basePackage }}.application.commands.user.UpdateLoginInfoCmd
import {{ basePackage }}.application.commands.user_behavior.RecordLoginLogCmd
import {{ basePackage }}.application.distributed.clients.CaptchaGenCli
import {{ basePackage }}.application.distributed.clients.CaptchaValidCli
import {{ basePackage }}.application.distributed.clients.authorize.IssueTokenCli
import {{ basePackage }}.application.distributed.clients.authorize.LogoutCli
import {{ basePackage }}.application.queries.authorize.AutoLoginQry
import {{ basePackage }}.application.queries.customer_profile.GetCustomerProfileQry
import {{ basePackage }}.application.queries.user.GetAccountInfoByEmailQry
import {{ basePackage }}.application.queries.user.GetUserByPhoneQry
import {{ basePackage }}.application.queries.user.GetUserCountInfoQry
import {{ basePackage }}.domain._share.meta.customer_profile.SCustomerProfile
import {{ basePackage }}.domain._share.meta.user.SUser
import {{ basePackage }}.domain.aggregates.user.enums.UserType
import {{ basePackage }}.domain.aggregates.user_login_log.enums.LoginResult
import {{ basePackage }}.domain.aggregates.user_login_log.enums.LoginType
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
