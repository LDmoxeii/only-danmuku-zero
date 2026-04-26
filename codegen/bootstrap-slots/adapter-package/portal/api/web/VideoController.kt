package {{ basePackage }}.adapter.portal.api.web

import cn.dev33.satoken.annotation.SaIgnore
import com.only.engine.satoken.utils.LoginHelper
import com.only4.cap4k.ddd.core.Mediator
import com.only4.cap4k.ddd.core.share.PageData
import {{ basePackage }}.adapter.portal.api.payload.video.ReportVideoPlayOnline
import {{ basePackage }}.adapter.portal.api.payload.video.GetVideoDetail
import {{ basePackage }}.adapter.portal.api.payload.video.GetVideoRecommendList
import {{ basePackage }}.adapter.portal.api.payload.video.GetVideoPage
import {{ basePackage }}.adapter.portal.api.payload.video.GetHotVidePage
import {{ basePackage }}.adapter.portal.api.payload.video.GetVideoPlist
import {{ basePackage }}.adapter.portal.api.payload.video.GetRecommendVideoList
import {{ basePackage }}.adapter.portal.api.payload.video.VideoSearch
import {{ basePackage }}.application.distributed.clients.statistics.ReportVideoPlayOnlineCli
import {{ basePackage }}.application.distributed.clients.statistics.ReportVideoSearchCountCli
import {{ basePackage }}.application.queries.customer_action.GetUserActionsByVideoIdQry
import {{ basePackage }}.application.queries.statistics.GetSearchKeywordTopListQry
import {{ basePackage }}.application.queries.video.GetHotVideoPageQry
import {{ basePackage }}.application.queries.video.GetRecommendVideosQry
import {{ basePackage }}.application.queries.video.GetVideoInfoQry
import {{ basePackage }}.application.queries.video_file.GetVideoFilesByVideoIdQry
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/video")
class VideoController {

    @SaIgnore
    @PostMapping("/getRecommendVideoList")
    fun getRecommendVideoList(): List<GetRecommendVideoList.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/page")
    fun page(@RequestBody @Validated request: GetVideoPage.Request): PageData<GetVideoPage.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/getVideoPList")
    fun getVideoPList(@RequestBody @Validated request: GetVideoPlist.Request): List<GetVideoPlist.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/detail")
    fun detail(@RequestBody @Validated request: GetVideoDetail.Request): GetVideoDetail.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/getVideoRecommendList")
    fun getVideoRecommendList(@RequestBody @Validated  request: GetVideoRecommendList.Request): List<GetVideoRecommendList.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/reportVideoPlayOnline")
    fun reportVideoPlayOnline(@RequestBody @Validated request: ReportVideoPlayOnline.Request): Long {
        TODO("Pending controller adapter contract implementation.")
    }
    @SaIgnore
    @PostMapping("/search")
    fun search(@RequestBody @Validated request: VideoSearch.Request): PageData<VideoSearch.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/getSearchKeywordTop")
    fun getSearchKeywordTop(): Collection<String> {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/getHotVidePage")
    fun getHotVidePage(@RequestBody @Validated request: GetHotVidePage.Request): PageData<GetHotVidePage.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

}
