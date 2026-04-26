package {{ basePackage }}.adapter.application.queries.category

import com.only4.cap4k.ddd.core.application.query.ListQuery
import {{ basePackage }}.application.queries.category.GetCategoryTreeQry
import org.springframework.stereotype.Service

/**
 * 获取分类树形结构
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2025/10/15
 */
@Service
class GetCategoryTreeQryHandler : ListQuery<GetCategoryTreeQry.Request, GetCategoryTreeQry.Response> {

    override fun exec(request: GetCategoryTreeQry.Request): List<GetCategoryTreeQry.Response> {
        TODO("Implement category tree query after recursive tree read model generation is available.")
    }
}
