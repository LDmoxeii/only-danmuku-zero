package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.application.queries._share.model.*
import edu.only4.danmuku.application.queries.video.GetHotVideoPageQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.springframework.stereotype.Service

/**
 * 获取热门视频列表
 */
@Service
class GetHotVideoPageQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetHotVideoPageQry.Request, GetHotVideoPageQry.Response> {

    override fun exec(request: GetHotVideoPageQry.Request): GetHotVideoPageQry.Response {
        val pageResult = sqlClient.createQuery(Video::class) {
            orderBy(table.playCount.desc(), table.likeCount.desc())
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
            })
        }.fetchPage(request.pageNum - 1, request.pageSize)

        return GetHotVideoPageQry.Response(
            page = PageData.create(
                pageNum = request.pageNum,
                pageSize = request.pageSize,
                list = pageResult.rows.map { video ->
                    GetHotVideoPageQry.Response.VideoItem(
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
