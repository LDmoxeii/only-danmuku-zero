package {{ basePackage }}.adapter.portal.api.web

import cn.dev33.satoken.annotation.SaIgnore
import com.only.engine.satoken.utils.LoginHelper
import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.share.PageData
import {{ basePackage }}.adapter.portal.api.payload.u_home.BindPhone
import {{ basePackage }}.adapter.portal.api.payload.u_home.CancelFocus
import {{ basePackage }}.adapter.portal.api.payload.u_home.Focus
import {{ basePackage }}.adapter.portal.api.payload.u_home.GetCustomerProfileDetail
import {{ basePackage }}.adapter.portal.api.payload.u_home.GetFansPage
import {{ basePackage }}.adapter.portal.api.payload.u_home.GetFocusPage
import {{ basePackage }}.adapter.portal.api.payload.u_home.GetCollectionPage
import {{ basePackage }}.adapter.portal.api.payload.u_home.GetVideoPage
import {{ basePackage }}.adapter.portal.api.payload.u_home.SaveTheme
import {{ basePackage }}.adapter.portal.api.payload.u_home.UpdateCustomerProfile
import {{ basePackage }}.application.commands.customer_focus.FocusCmd
import {{ basePackage }}.application.commands.customer_focus.UnFocusCmd
import {{ basePackage }}.application.commands.customer_profile.UpdateCustomerProfileCmd
import {{ basePackage }}.application.commands.customer_profile.BindPhoneCmd
import {{ basePackage }}.application.queries.customer_focus.CheckFocusStatusQry
import {{ basePackage }}.application.queries.customer_profile.GetCustomerProfileQry
import {{ basePackage }}.application.queries.statistics.GetTotalStatisticsInfoQry
import {{ basePackage }}.domain._share.meta.user.SUser
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/uHome")
class UHomeController {

    @SaIgnore
    @PostMapping("/customerProfile/detail")
    fun getCustomerProfileDetail(@RequestBody @Validated request: GetCustomerProfileDetail.Request): GetCustomerProfileDetail.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/customerProfile/update")
    fun update(@RequestBody @Validated request: UpdateCustomerProfile.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
    @PostMapping("/saveTheme")
    fun saveTheme(@RequestBody @Validated request: SaveTheme.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
    @PostMapping("/focus")
    fun focus(@RequestBody @Validated  request: Focus.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
    @PostMapping("/cancelFocus")
    fun cancelFocus(@RequestBody @Validated  request: CancelFocus.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
    @PostMapping("/getFocusPage")
    fun getFocusPage(@RequestBody @Validated request: GetFocusPage.Request): PageData<GetFocusPage.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/getFansPage")
    fun getFansPage(@RequestBody @Validated request: GetFansPage.Request): PageData<GetFansPage.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/getVideoPage")
    fun getVideoPage(@RequestBody @Validated request: GetVideoPage.Request): PageData<GetVideoPage.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/getCollectionPage")
    fun getCollectionPage(@RequestBody @Validated request: GetCollectionPage.Request): PageData<GetCollectionPage.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/bindPhone")
    fun bindPhone(@RequestBody @Validated request: BindPhone.Request) {
        TODO("Pending controller adapter contract implementation.")
    }

}
