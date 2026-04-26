package edu.only4.danmuku.adapter.application.queries.video_danmuku

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_danmuku.CheckDanmukuExistsQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class CheckDanmukuExistsQryHandler : Query<CheckDanmukuExistsQry.Request, CheckDanmukuExistsQry.Response> {

    override fun exec(request: CheckDanmukuExistsQry.Request): CheckDanmukuExistsQry.Response {
        return CheckDanmukuExistsQry.Response(
            exists = TODO("set exists")
        )
    }
}
