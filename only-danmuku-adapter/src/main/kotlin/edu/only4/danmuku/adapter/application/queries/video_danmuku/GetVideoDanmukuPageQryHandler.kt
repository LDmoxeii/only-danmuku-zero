package edu.only4.danmuku.adapter.application.queries.video_danmuku

import com.only4.cap4k.ddd.core.application.query.Query
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.application.queries._share.model.VideoDanmuku
import edu.only4.danmuku.application.queries._share.model.customer
import edu.only4.danmuku.application.queries._share.model.customerId
import edu.only4.danmuku.application.queries._share.model.fetchBy
import edu.only4.danmuku.application.queries._share.model.postTime
import edu.only4.danmuku.application.queries._share.model.video
import edu.only4.danmuku.application.queries._share.model.videoName
import edu.only4.danmuku.application.queries.video_danmuku.GetVideoDanmukuPageQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.`eq?`
import org.babyfish.jimmer.sql.kt.ast.expression.`ilike?`
import org.springframework.stereotype.Service

/**
 * 获取弹幕分页列表
 */
@Service
class GetVideoDanmukuPageQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetVideoDanmukuPageQry.Request, GetVideoDanmukuPageQry.Response> {

    override fun exec(request: GetVideoDanmukuPageQry.Request): GetVideoDanmukuPageQry.Response {
        val pageResult = sqlClient.createQuery(VideoDanmuku::class) {
            where(table.video.customerId `eq?` request.videoUserId)
            where(table.video.videoName `ilike?` request.videoNameFuzzy)
            orderBy(table.postTime.desc())
            select(table.fetchBy {
                text()
                mode()
                color()
                time()
                postTime()
                video {
                    videoName()
                    videoCover()
                }
                customer {
                    nickName()
                }
            })
        }.fetchPage(request.pageNum - 1, request.pageSize)

        return GetVideoDanmukuPageQry.Response(
            page = PageData.create(
                pageNum = request.pageNum,
                pageSize = request.pageSize,
                list = pageResult.rows.map { danmuku ->
                    GetVideoDanmukuPageQry.Response.DanmukuItem(
                        danmukuId = danmuku.id,
                        videoId = danmuku.video.id,
                        videoName = danmuku.video.videoName,
                        videoCover = danmuku.video.videoCover,
                        customerId = danmuku.customer.id,
                        customerNickname = danmuku.customer.nickName,
                        text = danmuku.text ?: "",
                        mode = danmuku.mode?.toInt() ?: 1,
                        color = danmuku.color ?: "#FFFFFF",
                        time = danmuku.time ?: 0,
                        postTime = danmuku.postTime ?: 0L
                    )
                },
                totalCount = pageResult.totalRowCount
            )
        )
    }
}
