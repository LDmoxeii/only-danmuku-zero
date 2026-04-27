package {{ basePackage }}.adapter.portal.api.web

import {{ basePackage }}.adapter.portal.api.payload.user_action.DoAction
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/userAction")
class UserActionController {

    @PostMapping("/doAction")
    fun doAction(@RequestBody @Validated request: DoAction.Request) {
        TODO("Pending controller adapter contract implementation.")
    }

}
