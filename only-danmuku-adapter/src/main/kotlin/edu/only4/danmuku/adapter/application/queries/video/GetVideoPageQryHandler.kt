package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.application.queries._share.model.*
import edu.only4.danmuku.application.queries.video.GetVideoPageQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.`eq?`
import org.babyfish.jimmer.sql.kt.ast.expression.`ilike?`
import org.babyfish.jimmer.sql.kt.ast.expression.`valueNotIn?`
import org.springframework.stereotype.Service

/**
 * 获取视频分页列表
 */
@Service
class GetVideoPageQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetVideoPageQry.Request, GetVideoPageQry.Response> {

    override fun exec(request: GetVideoPageQry.Request): GetVideoPageQry.Response {
        val pageResult = sqlClient.createQuery(Video::class) {
            where(table.customerId `eq?` request.userId)
            where(table.videoName `ilike?` request.videoNameFuzzy)
            where(table.parentCategoryId `eq?` request.categoryParentId)
            where(table.categoryId `eq?` request.categoryId)
            where(table.recommendType `eq?` request.recommendType)
            where(table.id `valueNotIn?` request.excludeVideoIds)
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
        }.fetchPage(request.pageNum - 1, request.pageSize)

        return GetVideoPageQry.Response(
            page = PageData.create(
                pageNum = request.pageNum,
                pageSize = request.pageSize,
                list = pageResult.rows.map { video ->
                    GetVideoPageQry.Response.VideoItem(
                        videoId = video.id,
                        videoCover = video.videoCover,
                        videoName = video.videoName,
                        userId = video.customerId,
                        createTime = video.createTime ?: 0L,
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
                        avatar = video.customer.relation?.avatar,
                        categoryFullName = video.parentCategory.name + (video.category?.name ?: "")
                    )
                },
                totalCount = pageResult.totalRowCount
            )
        )
    }
}
