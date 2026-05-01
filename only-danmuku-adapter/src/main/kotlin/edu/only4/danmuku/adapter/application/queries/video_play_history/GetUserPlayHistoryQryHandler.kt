package edu.only4.danmuku.adapter.application.queries.video_play_history

import com.only4.cap4k.ddd.core.application.query.Query
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.application.queries._share.model.VideoPlayHistory
import edu.only4.danmuku.application.queries._share.model.customerId
import edu.only4.danmuku.application.queries._share.model.fetchBy
import edu.only4.danmuku.application.queries._share.model.updateTime
import edu.only4.danmuku.application.queries.video_play_history.GetUserPlayHistoryQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

/**
 * 获取用户播放历史记录
 */
@Service
class GetUserPlayHistoryQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetUserPlayHistoryQry.Request, GetUserPlayHistoryQry.Response> {

    override fun exec(request: GetUserPlayHistoryQry.Request): GetUserPlayHistoryQry.Response {
        val pageResult = sqlClient.createQuery(VideoPlayHistory::class) {
            where(table.customerId eq request.customerId)
            orderBy(table.updateTime.desc())
            select(table.fetchBy {
                allScalarFields()
                video {
                    allScalarFields()
                }
                customerId()
            })
        }.fetchPage(request.pageNum - 1, request.pageSize)

        return GetUserPlayHistoryQry.Response(
            page = PageData.create(
                pageNum = request.pageNum,
                pageSize = request.pageSize,
                list = pageResult.rows.map { item ->
                    GetUserPlayHistoryQry.Response.HistoryItem(
                        historyId = item.id,
                        customerId = item.customerId,
                        videoId = item.video?.id,
                        videoName = item.video?.videoName,
                        videoCover = item.video?.videoCover,
                        fileIndex = item.fileIndex,
                        playTime = item.updateTime ?: item.createTime ?: 0L
                    )
                },
                totalCount = pageResult.totalRowCount
            )
        )
    }
}
