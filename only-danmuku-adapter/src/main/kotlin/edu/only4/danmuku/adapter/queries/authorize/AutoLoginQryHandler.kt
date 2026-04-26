package edu.only4.danmuku.adapter.queries.authorize

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.authorize.AutoLoginQry
import org.springframework.stereotype.Service

/**
 * 自动登录
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class AutoLoginQryHandler : Query<AutoLoginQry.Request, AutoLoginQry.Response> {

    override fun exec(request: AutoLoginQry.Request): AutoLoginQry.Response {
        return AutoLoginQry.Response(
            userId = TODO("set userId"),
            nickName = TODO("set nickName"),
            avatar = TODO("set avatar"),
            expireAt = TODO("set expireAt"),
            token = TODO("set token")
        )
    }
}
