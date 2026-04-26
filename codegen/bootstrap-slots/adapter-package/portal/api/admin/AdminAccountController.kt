package {{ basePackage }}.adapter.portal.api.admin

import cn.dev33.satoken.annotation.SaIgnore
import cn.dev33.satoken.stp.StpUtil
import com.only.engine.exception.BusinessException
import com.only.engine.misc.ServletUtils
import {{ basePackage }}.domain.shared.error.DanmukuBusinessErrors
import {{ basePackage }}.domain.shared.error.DanmukuAuthErrors
import com.only.engine.misc.ServletUtils.getClientIP
import com.only.engine.satoken.utils.LoginHelper
import com.only4.cap4k.ddd.core.Mediator
import {{ basePackage }}.adapter.portal.api.payload.admin_account.CheckCode
import {{ basePackage }}.adapter.portal.api.payload.admin_account.Login
import {{ basePackage }}.application.commands.user_behavior.RecordLoginLogCmd
import {{ basePackage }}.application.distributed.clients.CaptchaGenCli
import {{ basePackage }}.application.distributed.clients.CaptchaValidCli
import {{ basePackage }}.application.distributed.clients.authorize.IssueTokenCli
import {{ basePackage }}.application.distributed.clients.authorize.LogoutCli
import {{ basePackage }}.application.queries.user.GetAccountInfoByEmailQry
import {{ basePackage }}.domain.aggregates.user.User
import {{ basePackage }}.domain.aggregates.user.enums.UserType
import {{ basePackage }}.domain.aggregates.user_login_log.enums.LoginResult
import {{ basePackage }}.domain.aggregates.user_login_log.enums.LoginType
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
