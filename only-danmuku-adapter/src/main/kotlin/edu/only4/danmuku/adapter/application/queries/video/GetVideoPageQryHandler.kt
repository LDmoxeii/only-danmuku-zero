package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video.GetVideoPageQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetVideoPageQryHandler : Query<GetVideoPageQry.Request, GetVideoPageQry.Response> {

    override fun exec(request: GetVideoPageQry.Request): GetVideoPageQry.Response {
        return GetVideoPageQry.Response(
            videoId = TODO("set videoId"),
            videoCover = TODO("set videoCover"),
            videoName = TODO("set videoName"),
            userId = TODO("set userId"),
            createTime = TODO("set createTime"),
            lastUpdateTime = TODO("set lastUpdateTime"),
            parentCategoryId = TODO("set parentCategoryId"),
            categoryId = TODO("set categoryId"),
            postType = TODO("set postType"),
            originInfo = TODO("set originInfo"),
            tags = TODO("set tags"),
            introduction = TODO("set introduction"),
            duration = TODO("set duration"),
            status = TODO("set status"),
            playCount = TODO("set playCount"),
            likeCount = TODO("set likeCount"),
            danmukuCount = TODO("set danmukuCount"),
            commentCount = TODO("set commentCount"),
            coinCount = TODO("set coinCount"),
            collectCount = TODO("set collectCount"),
            recommendType = TODO("set recommendType"),
            lastPlayTime = TODO("set lastPlayTime"),
            nickName = TODO("set nickName"),
            avatar = TODO("set avatar"),
            categoryFullName = TODO("set categoryFullName")
        )
    }
}
