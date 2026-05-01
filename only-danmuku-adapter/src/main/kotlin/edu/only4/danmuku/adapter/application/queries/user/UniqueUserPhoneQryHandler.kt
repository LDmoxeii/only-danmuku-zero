package edu.only4.danmuku.adapter.application.queries.user

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.user.UniqueUserPhoneQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class UniqueUserPhoneQryHandler : Query<UniqueUserPhoneQry.Request, UniqueUserPhoneQry.Response> {

    override fun exec(request: UniqueUserPhoneQry.Request): UniqueUserPhoneQry.Response {
        return UniqueUserPhoneQry.Response(
            exists = TODO("set exists")
        )
    }
}
