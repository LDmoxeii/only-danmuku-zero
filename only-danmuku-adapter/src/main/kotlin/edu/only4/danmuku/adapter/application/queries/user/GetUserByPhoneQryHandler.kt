package edu.only4.danmuku.adapter.application.queries.user

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.user.GetUserByPhoneQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetUserByPhoneQryHandler : Query<GetUserByPhoneQry.Request, GetUserByPhoneQry.Response> {

    override fun exec(request: GetUserByPhoneQry.Request): GetUserByPhoneQry.Response {
        return GetUserByPhoneQry.Response(
            userId = TODO("set userId"),
            nickName = TODO("set nickName"),
            type = TODO("set type")
        )
    }
}
