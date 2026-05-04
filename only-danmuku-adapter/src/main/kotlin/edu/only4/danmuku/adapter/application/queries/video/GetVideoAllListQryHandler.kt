package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.Video
import edu.only4.danmuku.application.queries._share.model.createTime
import edu.only4.danmuku.application.queries._share.model.customerId
import edu.only4.danmuku.application.queries._share.model.fetchBy
import edu.only4.danmuku.application.queries.video.GetVideoAllListQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.`eq?`
import org.springframework.stereotype.Service

/**
 * 获取视频全量列表
 */
@Service
class GetVideoAllListQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetVideoAllListQry.Request, GetVideoAllListQry.Response> {

    override fun exec(request: GetVideoAllListQry.Request): GetVideoAllListQry.Response {
        val items = sqlClient.createQuery(Video::class) {
            where(table.customerId `eq?` request.userId)
            orderBy(table.createTime.desc())
            select(table.fetchBy {
                allScalarFields()
                customer {
                    allScalarFields()
                    relation {
                        allScalarFields()
                    }
                }
                parentCategory {
                    allScalarFields()
                }
                category {
                    allScalarFields()
                }
                videoPost {
                    status()
                }
            })
        }.execute().map { video ->
            GetVideoAllListQry.Response.VideoItem(
                videoId = video.id,
                videoCover = video.videoCover,
                videoName = video.videoName,
                userId = video.customerId,
                createTime = video.createTime!!,
                lastUpdateTime = video.updateTime,
                parentCategoryId = video.parentCategoryId,
                categoryId = video.categoryId,
                postType = video.postType,
                originInfo = video.originInfo,
                tags = video.tags,
                introduction = video.introduction,
                duration = video.duration,
                status = video.videoPost.status,
                playCount = video.playCount,
                likeCount = video.likeCount,
                danmukuCount = video.danmukuCount,
                commentCount = video.commentCount,
                coinCount = video.coinCount,
                collectCount = video.collectCount,
                recommendType = video.recommendType,
                lastPlayTime = video.lastPlayTime,
                nickName = video.customer.nickName,
                avatar = video.customer.relation!!.avatar,
                categoryFullName = video.parentCategory.name + video.category?.name,
            )
        }

        return GetVideoAllListQry.Response(
            items = items
        )
    }
}
