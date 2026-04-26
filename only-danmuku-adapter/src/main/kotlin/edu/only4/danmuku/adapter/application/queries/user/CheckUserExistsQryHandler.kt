package edu.only4.danmuku.adapter.application.queries.user

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.user.CheckUserExistsQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class CheckUserExistsQryHandler : Query<CheckUserExistsQry.Request, CheckUserExistsQry.Response> {

    override fun exec(request: CheckUserExistsQry.Request): CheckUserExistsQry.Response {
        return CheckUserExistsQry.Response(
            exists = TODO("set exists")
        )
    }
}
