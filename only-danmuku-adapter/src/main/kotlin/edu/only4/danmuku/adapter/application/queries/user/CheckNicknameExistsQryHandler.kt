package edu.only4.danmuku.adapter.application.queries.user

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.user.CheckNicknameExistsQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class CheckNicknameExistsQryHandler : Query<CheckNicknameExistsQry.Request, CheckNicknameExistsQry.Response> {

    override fun exec(request: CheckNicknameExistsQry.Request): CheckNicknameExistsQry.Response {
        return CheckNicknameExistsQry.Response(
            exists = TODO("set exists")
        )
    }
}
