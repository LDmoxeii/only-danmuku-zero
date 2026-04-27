package {{ basePackage }}.adapter.portal.api.web

import com.only4.cap4k.ddd.core.share.PageData
import {{ basePackage }}.adapter.portal.api.payload.user_message.DeleteMessage
import {{ basePackage }}.adapter.portal.api.payload.user_message.GetMessagePage
import {{ basePackage }}.adapter.portal.api.payload.user_message.GetNoReadCountGroup
import {{ basePackage }}.adapter.portal.api.payload.user_message.ReadAllMessage
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 用户消息控制器
 */
@RestController
@RequestMapping("/message")
class UserMessageController {

    /**
     * 获取未读消息数
     */
    @PostMapping("/getNoReadCount")
    fun getNoReadCount(): Long {
        TODO("Pending controller adapter contract implementation.")
    }
    @PostMapping("/getNoReadCountGroup")
    fun getNoReadCountGroup(): List<GetNoReadCountGroup.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    /**
     * 标记全部已读
     */
    @PostMapping("/readAll")
    fun readAll(@RequestBody @Validated request: ReadAllMessage.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
    @PostMapping("/page")
    fun page(@RequestBody @Validated request: GetMessagePage.Request): PageData<GetMessagePage.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/delete")
    fun delete(@RequestBody @Validated request: DeleteMessage.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
}
