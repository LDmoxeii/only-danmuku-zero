package {{ basePackage }}.adapter.portal.api.admin

import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.share.PageData
import {{ basePackage }}.adapter.portal.api.payload.admin_user.ChangeStatus
import {{ basePackage }}.adapter.portal.api.payload.admin_user.GetUserPage
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/user")
class AdminUserController {

    @PostMapping("/page")
    fun page(@RequestBody @Validated request: GetUserPage.Request): PageData<GetUserPage.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/changeStatus")
    fun changeStatus(@RequestBody @Validated request: ChangeStatus.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
}
