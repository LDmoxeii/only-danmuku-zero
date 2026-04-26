package {{ basePackage }}.adapter.portal.api.web

import cn.dev33.satoken.annotation.SaIgnore
import com.only.engine.satoken.utils.LoginHelper
import com.only4.cap4k.ddd.core.Mediator
import {{ basePackage }}.adapter.portal.api.payload.video_series.ChangeVideoSeriesSort
import {{ basePackage }}.adapter.portal.api.payload.video_series.DeleteSeriesVideo
import {{ basePackage }}.adapter.portal.api.payload.video_series.DeleteVideoSeries
import {{ basePackage }}.adapter.portal.api.payload.video_series.VideoSeriesLoad
import {{ basePackage }}.adapter.portal.api.payload.video_series.GetAllSeriesVideoList
import {{ basePackage }}.adapter.portal.api.payload.video_series.GetVideoSeriesDetail
import {{ basePackage }}.adapter.portal.api.payload.video_series.LoadVideoSeriesWithVideo
import {{ basePackage }}.adapter.portal.api.payload.video_series.SaveSeriesVideo
import {{ basePackage }}.adapter.portal.api.payload.video_series.SaveVideoSeries
import {{ basePackage }}.application.commands.customer_video_series.*
import {{ basePackage }}.application.queries.customer_video_series.GetCustomerVideoSeriesInfoQry
import {{ basePackage }}.application.queries.customer_video_series.GetCustomerVideoSeriesListQry
import {{ basePackage }}.application.queries.customer_video_series.GetCustomerVideoSeriesVideoQry
import {{ basePackage }}.application.queries.video.GetVideoAllList
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/uHome/series")
@Validated
class VideoSeriesController {

    @SaIgnore
    @PostMapping("/getVideoSeries")
    fun getVideoSeries(@RequestBody @Validated request: VideoSeriesLoad.Request): VideoSeriesLoad.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/detail")
    fun detail(@RequestBody @Validated request: GetVideoSeriesDetail.Request): GetVideoSeriesDetail.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/getAllVideoList")
    fun getAllVideoList(@RequestBody @Validated request: GetAllSeriesVideoList.Request): GetAllSeriesVideoList.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/saveVideoSeries")
    fun saveVideoSeries(@RequestBody @Validated request: SaveVideoSeries.Request): SaveVideoSeries.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @SaIgnore
    @PostMapping("/loadVideoSeriesWithVideo")
    fun loadVideoSeriesWithVideo(
        @RequestBody @Validated request: LoadVideoSeriesWithVideo.Request
    ): LoadVideoSeriesWithVideo.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/saveSeriesVideo")
    fun saveSeriesVideo(@RequestBody @Validated request: SaveSeriesVideo.Request): SaveSeriesVideo.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/delSeriesVideo")
    fun deleteSeriesVideo(@RequestBody @Validated request: DeleteSeriesVideo.Request): DeleteSeriesVideo.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/delVideoSeries")
    fun deleteVideoSeries(@RequestBody @Validated request: DeleteVideoSeries.Request): DeleteVideoSeries.Response {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/changeSort")
    fun changeSort(@RequestBody @Validated request: ChangeVideoSeriesSort.Request): ChangeVideoSeriesSort.Response {
        TODO("Pending controller adapter contract implementation.")
    }
}
