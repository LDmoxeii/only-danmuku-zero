package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video.GetRecommendVideosQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetRecommendVideosQryHandler : Query<GetRecommendVideosQry.Request, GetRecommendVideosQry.Response> {

    override fun exec(request: GetRecommendVideosQry.Request): GetRecommendVideosQry.Response {
        return GetRecommendVideosQry.Response(
            videoId = TODO("set videoId"),
            videoCover = TODO("set videoCover"),
            videoName = TODO("set videoName"),
            userId = TODO("set userId"),
            nickName = TODO("set nickName"),
            avatar = TODO("set avatar"),
            playCount = TODO("set playCount"),
            likeCount = TODO("set likeCount"),
            createTime = TODO("set createTime")
        )
    }
}
