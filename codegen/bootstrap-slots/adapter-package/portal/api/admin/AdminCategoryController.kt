package {{ basePackage }}.adapter.portal.api.admin

import com.only4.cap4k.ddd.core.Mediator
import {{ basePackage }}.adapter.portal.api.payload.admin_category.ChangeCategorySort
import {{ basePackage }}.adapter.portal.api.payload.admin_category.GetCategoryTree
import {{ basePackage }}.adapter.portal.api.payload.admin_category.SaveCategory
import {{ basePackage }}.adapter.portal.api.payload.admin_category.UpdateCategory
import {{ basePackage }}.application.commands.category.DeleteCategoryCmd
import {{ basePackage }}.application.commands.category.UpdateCategorySortOrderCmd
import {{ basePackage }}.application.queries.category.GetCategoryTreeQry
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 管理员分类管理控制器
 */
@RestController
@RequestMapping("/admin/category")
class AdminCategoryController {

    @PostMapping("/getCategoryTree")
    fun getCategoryTree(): List<GetCategoryTree.Response> {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/save")
    fun save(@RequestBody @Validated request: SaveCategory.Request) {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/update")
    fun update(@RequestBody @Validated request: UpdateCategory.Request) {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/delete")
    fun delete(@RequestBody @Validated request: DeleteCategoryCmd.Request) {
        TODO("Pending controller adapter contract implementation.")
    }

    @PostMapping("/changeSort")
    fun changeSort(@RequestBody @Validated request: ChangeCategorySort.Request) {
        TODO("Pending controller adapter contract implementation.")
    }

}
