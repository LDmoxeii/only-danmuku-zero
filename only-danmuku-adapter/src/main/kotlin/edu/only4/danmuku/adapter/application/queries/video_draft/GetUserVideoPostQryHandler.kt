package edu.only4.danmuku.adapter.application.queries.video_draft

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_draft.GetUserVideoPostQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetUserVideoPostQryHandler : Query<GetUserVideoPostQry.Request, GetUserVideoPostQry.Response> {

    override fun exec(request: GetUserVideoPostQry.Request): GetUserVideoPostQry.Response {
        return GetUserVideoPostQry.Response(
            videoPostId = TODO("set videoPostId"),
            videoId = TODO("set videoId"),
            videoCover = TODO("set videoCover"),
            videoName = TODO("set videoName"),
            duration = TODO("set duration"),
            createTime = TODO("set createTime"),
            lastUpdateTime = TODO("set lastUpdateTime"),
            status = TODO("set status"),
            interaction = TODO("set interaction"),
            playCount = TODO("set playCount"),
            likeCount = TODO("set likeCount"),
            danmukuCount = TODO("set danmukuCount"),
            commentCount = TODO("set commentCount"),
            coinCount = TODO("set coinCount"),
            collectCount = TODO("set collectCount")
        )
    }
}
