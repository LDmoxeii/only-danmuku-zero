package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video.GetHotVideoPageQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetHotVideoPageQryHandler : Query<GetHotVideoPageQry.Request, GetHotVideoPageQry.Response> {

    override fun exec(request: GetHotVideoPageQry.Request): GetHotVideoPageQry.Response {
        return GetHotVideoPageQry.Response(
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
