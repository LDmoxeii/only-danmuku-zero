package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video.GetVideoInfoQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetVideoInfoQryHandler : Query<GetVideoInfoQry.Request, GetVideoInfoQry.Response> {

    override fun exec(request: GetVideoInfoQry.Request): GetVideoInfoQry.Response {
        return GetVideoInfoQry.Response(
            videoId = TODO("set videoId"),
            videoCover = TODO("set videoCover"),
            videoName = TODO("set videoName"),
            userId = TODO("set userId"),
            createTime = TODO("set createTime"),
            postType = TODO("set postType"),
            originInfo = TODO("set originInfo"),
            tags = TODO("set tags"),
            introduction = TODO("set introduction"),
            interaction = TODO("set interaction"),
            playCount = TODO("set playCount"),
            likeCount = TODO("set likeCount"),
            danmukuCount = TODO("set danmukuCount"),
            commentCount = TODO("set commentCount"),
            coinCount = TODO("set coinCount"),
            collectCount = TODO("set collectCount"),
            nickName = TODO("set nickName"),
            avatar = TODO("set avatar"),
            duration = TODO("set duration")
        )
    }
}
