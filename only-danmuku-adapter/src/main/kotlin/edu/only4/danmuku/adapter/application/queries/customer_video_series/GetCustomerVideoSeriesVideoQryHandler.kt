package edu.only4.danmuku.adapter.application.queries.customer_video_series

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.CustomerVideoSeries
import edu.only4.danmuku.application.queries._share.model.customerId
import edu.only4.danmuku.application.queries._share.model.fetchBy
import edu.only4.danmuku.application.queries.customer_video_series.GetCustomerVideoSeriesVideoQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

/**
 * 获取用户视频系列及关联视频
 */
@Service
class GetCustomerVideoSeriesVideoQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetCustomerVideoSeriesVideoQry.Request, GetCustomerVideoSeriesVideoQry.Response> {

    override fun exec(request: GetCustomerVideoSeriesVideoQry.Request): GetCustomerVideoSeriesVideoQry.Response {
        val seriesList = sqlClient.createQuery(CustomerVideoSeries::class) {
            where(table.customerId eq request.userId)
            select(table.fetchBy {
                seriesName()
                seriesDescription()
                sort()
                seriesVideos {
                    sort()
                    video {
                        videoCover()
                        videoName()
                        playCount()
                    }
                }
            })
        }.execute()

        return GetCustomerVideoSeriesVideoQry.Response(
            items = seriesList.map { series ->
                GetCustomerVideoSeriesVideoQry.Response.SeriesItem(
                    seriesId = series.id,
                    seriesName = series.seriesName,
                    seriesDescription = series.seriesDescription,
                    sort = series.sort,
                    videoList = series.seriesVideos.map { seriesVideo ->
                        GetCustomerVideoSeriesVideoQry.Response.VideoItem(
                            videoId = seriesVideo.video.id,
                            videoCover = seriesVideo.video.videoCover,
                            videoName = seriesVideo.video.videoName,
                            playCount = seriesVideo.video.playCount,
                            sort = seriesVideo.sort
                        )
                    }
                )
            }
        )
    }
}
