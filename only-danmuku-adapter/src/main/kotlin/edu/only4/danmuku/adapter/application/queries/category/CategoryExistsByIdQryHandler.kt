package edu.only4.danmuku.adapter.application.queries.category

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.category.CategoryExistsByIdQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class CategoryExistsByIdQryHandler : Query<CategoryExistsByIdQry.Request, CategoryExistsByIdQry.Response> {

    override fun exec(request: CategoryExistsByIdQry.Request): CategoryExistsByIdQry.Response {
        return CategoryExistsByIdQry.Response(
            exists = TODO("set exists")
        )
    }
}
