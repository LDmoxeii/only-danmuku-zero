package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video.CheckVideosOwnershipQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class CheckVideosOwnershipQryHandler : Query<CheckVideosOwnershipQry.Request, CheckVideosOwnershipQry.Response> {

    override fun exec(request: CheckVideosOwnershipQry.Request): CheckVideosOwnershipQry.Response {
        return CheckVideosOwnershipQry.Response(
            allOwned = TODO("set allOwned"),
            missing = TODO("set missing")
        )
    }
}
