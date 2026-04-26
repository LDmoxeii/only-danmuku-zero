package edu.only4.danmuku.adapter.application.queries.message

import com.only4.cap4k.ddd.core.application.query.PageQuery
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.application.queries.message.GetMessagePageQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetMessagePageQryHandler : PageQuery<GetMessagePageQry.Request, GetMessagePageQry.Response> {

    override fun exec(request: GetMessagePageQry.Request): PageData<GetMessagePageQry.Response> {
        return PageData.create(request, 1L, listOf(
            GetMessagePageQry.Response(
                id = TODO("set id"),
                messageType = TODO("set messageType"),
                readType = TODO("set readType"),
                extendJson = TODO("set extendJson"),
                createTime = TODO("set createTime"),
                videoPostId = TODO("set videoPostId"),
                videoId = TODO("set videoId"),
                videoName = TODO("set videoName"),
                videoCover = TODO("set videoCover"),
                sendUserId = TODO("set sendUserId"),
                sendUserName = TODO("set sendUserName"),
                sendUserAvatar = TODO("set sendUserAvatar")
            )
        ))
    }
}
