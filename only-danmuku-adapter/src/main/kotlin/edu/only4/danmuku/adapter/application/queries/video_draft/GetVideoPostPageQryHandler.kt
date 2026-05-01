package edu.only4.danmuku.adapter.application.queries.video_draft

import com.only4.cap4k.ddd.core.application.query.Query
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.application.queries._share.model.*
import edu.only4.danmuku.application.queries.video_draft.GetVideoPostPageQry
import edu.only4.danmuku.domain.aggregates.video.enums.RecommendType
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.*
import org.springframework.stereotype.Service

/**
 * 获取视频投稿分页列表
 */
@Service
class GetVideoPostPageQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetVideoPostPageQry.Request, GetVideoPostPageQry.Response> {

    override fun exec(request: GetVideoPostPageQry.Request): GetVideoPostPageQry.Response {
        val pageResult = sqlClient.createQuery(VideoPost::class) {
            where(table.customerId `eq?` request.userId)
            where(table.videoName `ilike?` request.videoNameFuzzy)
            where(table.parentCategoryId `eq?` request.categoryParentId)
            where(table.categoryId `eq?` request.categoryId)
            where(table.id `valueNotIn?` request.excludeVideoIds)
            when (request.recommendType) {
                RecommendType.RECOMMEND -> where(table.video.recommendType eq RecommendType.RECOMMEND)
                RecommendType.NOT_RECOMMEND -> {
                    val video = table.`video?`
                    where(or(video.id.isNull(), video.recommendType eq RecommendType.NOT_RECOMMEND))
                }
                else -> Unit
            }
            orderBy(table.createTime.desc())
            select(table.fetchBy {
                allScalarFields()
                video {
                    allScalarFields()
                }
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

        return GetVideoPostPageQry.Response(
            page = PageData.create(
                pageNum = request.pageNum,
                pageSize = request.pageSize,
                list = pageResult.rows.map { post ->
                    GetVideoPostPageQry.Response.VideoPostItem(
                        videoId = post.id,
                        videoCover = post.videoCover,
                        videoName = post.videoName,
                        userId = post.customerId,
                        createTime = post.createTime ?: 0,
                        lastUpdateTime = post.updateTime,
                        parentCategoryId = post.parentCategoryId,
                        categoryId = post.categoryId,
                        postType = post.postType,
                        originInfo = post.originInfo,
                        tags = post.tags,
                        introduction = post.introduction,
                        duration = post.duration,
                        status = post.status,
                        playCount = post.video?.playCount ?: 0,
                        likeCount = post.video?.likeCount ?: 0,
                        danmukuCount = post.video?.danmukuCount ?: 0,
                        commentCount = post.video?.commentCount ?: 0,
                        coinCount = post.video?.coinCount ?: 0,
                        collectCount = post.video?.collectCount ?: 0,
                        recommendType = post.video?.recommendType ?: RecommendType.NOT_RECOMMEND,
                        lastPlayTime = post.video?.lastPlayTime ?: 0,
                        nickName = post.customer.nickName,
                        avatar = post.customer.relation?.avatar,
                        categoryFullName = post.parentCategory.name + post.category?.name
                    )
                },
                totalCount = pageResult.totalRowCount
            )
        )
    }
}
