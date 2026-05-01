package edu.only4.danmuku.adapter.application.queries.video_danmuku

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.VideoDanmuku
import edu.only4.danmuku.application.queries._share.model.customer
import edu.only4.danmuku.application.queries._share.model.fetchBy
import edu.only4.danmuku.application.queries._share.model.fileId
import edu.only4.danmuku.application.queries._share.model.time
import edu.only4.danmuku.application.queries._share.model.video
import edu.only4.danmuku.application.queries._share.model.videoId
import edu.only4.danmuku.application.queries.video_danmuku.GetDanmukuListByFileIdQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.asc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

/**
 * 根据文件ID获取弹幕
 */
@Service
class GetDanmukuListByFileIdQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetDanmukuListByFileIdQry.Request, GetDanmukuListByFileIdQry.Response> {

    override fun exec(request: GetDanmukuListByFileIdQry.Request): GetDanmukuListByFileIdQry.Response {
        val danmukus = sqlClient.createQuery(VideoDanmuku::class) {
            where(table.fileId eq request.fileId)
            where(table.videoId eq request.videoId)
            orderBy(table.time.asc())
            select(table.fetchBy {
                allScalarFields()
                customer {
                    allScalarFields()
                }
                video {
                    allScalarFields()
                }
            })
        }.execute()

        return GetDanmukuListByFileIdQry.Response(
            items = danmukus.map { danmuku ->
                GetDanmukuListByFileIdQry.Response.DanmukuItem(
                    danmukuId = danmuku.id,
                    fileId = danmuku.fileId,
                    videoId = danmuku.video.id,
                    userId = danmuku.customer.id,
                    text = danmuku.text ?: "",
                    mode = danmuku.mode?.toInt() ?: 1,
                    color = danmuku.color ?: "#FFFFFF",
                    time = danmuku.time ?: 0,
                    postTime = danmuku.postTime ?: 0L,
                    videoName = danmuku.video.videoName,
                    videoCover = danmuku.video.videoCover,
                    nickName = danmuku.customer.nickName
                )
            }
        )
    }
}
