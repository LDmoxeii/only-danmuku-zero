package edu.only4.danmuku.adapter.application.queries.message

import com.only.engine.satoken.utils.LoginHelper
import com.only4.cap4k.ddd.core.application.query.Query
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.application.queries._share.model.CustomerMessage
import edu.only4.danmuku.application.queries._share.model.CustomerProfile
import edu.only4.danmuku.application.queries._share.model.VideoPost
import edu.only4.danmuku.application.queries._share.model.customerId
import edu.only4.danmuku.application.queries._share.model.fetchBy
import edu.only4.danmuku.application.queries._share.model.id
import edu.only4.danmuku.application.queries._share.model.messageType
import edu.only4.danmuku.application.queries._share.model.userId
import edu.only4.danmuku.application.queries._share.model.video
import edu.only4.danmuku.application.queries.message.GetMessagePageQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.valueIn
import org.springframework.stereotype.Service

/**
 * 获取消息分页
 */
@Service
class GetMessagePageQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetMessagePageQry.Request, GetMessagePageQry.Response> {

    override fun exec(request: GetMessagePageQry.Request): GetMessagePageQry.Response {
        val currentUserId = LoginHelper.getUserId()
            ?: return GetMessagePageQry.Response(
                page = PageData.empty(request.pageSize, request.pageNum)
            )
        val page = sqlClient.createQuery(CustomerMessage::class) {
            where(table.customerId eq currentUserId)
            request.messageType?.let { where(table.messageType eq it) }
            orderBy(table.id.desc())
            select(table)
        }.fetchPage(request.pageNum - 1, request.pageSize)
        val sendUserIds = page.rows.mapNotNull { it.sendSubjectId }.toSet()
        val videoIds = page.rows.mapNotNull { it.videoId }.toSet()
        val profileMap: Map<Long, CustomerProfile> = if (sendUserIds.isNotEmpty()) {
            sqlClient.createQuery(CustomerProfile::class) {
                where(table.userId valueIn sendUserIds)
                select(table)
            }.execute().associateBy { it.userId }
        } else {
            emptyMap()
        }
        val videoMap: Map<Long, VideoPost> = if (videoIds.isNotEmpty()) {
            sqlClient.createQuery(VideoPost::class) {
                where(table.id valueIn videoIds)
                select(table.fetchBy {
                    allScalarFields()
                    video {
                        videoName()
                        videoCover()
                    }
                })
            }.execute().associateBy { it.id }
        } else {
            emptyMap()
        }

        return GetMessagePageQry.Response(
            page = PageData.create(
                pageNum = request.pageNum,
                pageSize = request.pageSize,
                totalCount = page.totalRowCount,
                list = page.rows.map { row ->
                    GetMessagePageQry.Response.MessageItem(
                        id = row.id,
                        messageType = row.messageType,
                        readType = row.readType,
                        extendJson = row.extendJson,
                        createTime = row.createTime ?: 0L,
                        videoPostId = row.videoId,
                        videoId = row.videoId?.let { vid -> videoMap[vid]?.video?.id },
                        videoName = row.videoId?.let { vid -> videoMap[vid]?.video?.videoName },
                        videoCover = row.videoId?.let { vid -> videoMap[vid]?.video?.videoCover },
                        sendUserId = row.sendSubjectId,
                        sendUserName = row.sendSubjectId?.let { sid -> profileMap[sid]?.nickName },
                        sendUserAvatar = row.sendSubjectId?.let { sid -> profileMap[sid]?.avatar },
                    )
                }
            )
        )
    }
}
