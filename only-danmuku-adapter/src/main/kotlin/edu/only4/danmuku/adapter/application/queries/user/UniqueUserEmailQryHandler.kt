package edu.only4.danmuku.adapter.application.queries.user

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.user.UniqueUserEmailQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class UniqueUserEmailQryHandler : Query<UniqueUserEmailQry.Request, UniqueUserEmailQry.Response> {

    override fun exec(request: UniqueUserEmailQry.Request): UniqueUserEmailQry.Response {
        return UniqueUserEmailQry.Response(
            exists = TODO("set exists")
        )
    }
}
