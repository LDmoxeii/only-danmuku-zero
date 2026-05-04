package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.Video
import edu.only4.danmuku.application.queries._share.model.fetchBy
import edu.only4.danmuku.application.queries._share.model.playCount
import edu.only4.danmuku.application.queries._share.model.recommendType
import edu.only4.danmuku.application.queries.video.GetRecommendVideosQry
import edu.only4.danmuku.domain.aggregates.video.enums.RecommendType
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

/**
 * 获取推荐视频列表
 */
@Service
class GetRecommendVideosQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetRecommendVideosQry.Request, GetRecommendVideosQry.Response> {

    override fun exec(request: GetRecommendVideosQry.Request): GetRecommendVideosQry.Response {
        val videos = sqlClient.createQuery(Video::class) {
            where(table.recommendType eq RecommendType.RECOMMEND)
            orderBy(table.playCount.desc())
            select(table.fetchBy {
                createTime()
                videoCover()
                videoName()
                playCount()
                likeCount()
                customer {
                    relation {
                        nickName()
                        avatar()
                    }
                }
            })
        }.execute()

        return GetRecommendVideosQry.Response(
            items = videos.map { video ->
                GetRecommendVideosQry.Response.VideoItem(
                    videoId = video.id,
                    videoCover = video.videoCover,
                    videoName = video.videoName,
                    userId = video.customer.id,
                    nickName = video.customer.relation?.nickName,
                    avatar = video.customer.relation?.avatar,
                    playCount = video.playCount,
                    likeCount = video.likeCount,
                    createTime = video.createTime ?: 0L
                )
            }
        )
    }
}
