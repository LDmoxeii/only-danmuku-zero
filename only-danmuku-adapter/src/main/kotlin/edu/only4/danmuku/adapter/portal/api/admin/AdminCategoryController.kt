package edu.only4.danmuku.adapter.portal.api.admin

import edu.only4.danmuku.adapter.portal.api.payload.admin_category.ChangeCategorySort
import edu.only4.danmuku.adapter.portal.api.payload.admin_category.GetCategoryTree
import edu.only4.danmuku.adapter.portal.api.payload.admin_category.SaveCategory
import edu.only4.danmuku.adapter.portal.api.payload.admin_category.UpdateCategory
import edu.only4.danmuku.application.commands.category.DeleteCategoryCmd
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
