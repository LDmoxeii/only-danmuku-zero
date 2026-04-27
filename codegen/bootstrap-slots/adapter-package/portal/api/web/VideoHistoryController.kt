package {{ basePackage }}.adapter.portal.api.web

import com.only4.cap4k.ddd.core.share.PageData
import {{ basePackage }}.adapter.portal.api.payload.video_history.DeleteVideoHistory
import {{ basePackage }}.adapter.portal.api.payload.video_history.GetHistoryPage
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 播放历史控制器
 */
@RestController
@RequestMapping("/history")
class VideoHistoryController {

    /**
     * 加载播放历史
     */
    @PostMapping("/page")
    fun page(@RequestBody @Validated request: GetHistoryPage.Request): PageData<GetHistoryPage.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    /**
     * 清空播放历史
     */
    @PostMapping("/cleanHistory")
    fun historyClean() {
        TODO("Pending controller adapter contract implementation.")
    }
    @PostMapping("/delete")
    fun delete(@RequestBody @Validated request: DeleteVideoHistory.Request) {
        TODO("Pending controller adapter contract implementation.")
    }
}
