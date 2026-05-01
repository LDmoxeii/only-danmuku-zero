package edu.only4.danmuku.adapter.application.queries.category

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.category.GetCategoryTreeQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetCategoryTreeQryHandler : Query<GetCategoryTreeQry.Request, GetCategoryTreeQry.Response> {

    override fun exec(request: GetCategoryTreeQry.Request): GetCategoryTreeQry.Response {
        return GetCategoryTreeQry.Response(
            items = TODO("set items")
        )
    }
}
