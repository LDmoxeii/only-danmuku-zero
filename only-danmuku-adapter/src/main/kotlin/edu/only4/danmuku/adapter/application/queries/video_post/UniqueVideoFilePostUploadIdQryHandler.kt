package edu.only4.danmuku.adapter.application.queries.video_post

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_post.UniqueVideoFilePostUploadIdQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class UniqueVideoFilePostUploadIdQryHandler : Query<UniqueVideoFilePostUploadIdQry.Request, UniqueVideoFilePostUploadIdQry.Response> {

    override fun exec(request: UniqueVideoFilePostUploadIdQry.Request): UniqueVideoFilePostUploadIdQry.Response {
        return UniqueVideoFilePostUploadIdQry.Response(
            exists = TODO("set exists")
        )
    }
}
