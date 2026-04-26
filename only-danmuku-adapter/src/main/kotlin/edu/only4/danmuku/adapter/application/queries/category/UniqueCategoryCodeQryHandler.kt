package edu.only4.danmuku.adapter.application.queries.category

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.category.UniqueCategoryCodeQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class UniqueCategoryCodeQryHandler : Query<UniqueCategoryCodeQry.Request, UniqueCategoryCodeQry.Response> {

    override fun exec(request: UniqueCategoryCodeQry.Request): UniqueCategoryCodeQry.Response {
        return UniqueCategoryCodeQry.Response(
            exists = TODO("set exists")
        )
    }
}
