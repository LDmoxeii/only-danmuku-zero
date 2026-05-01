
package edu.only4.danmuku.application.queries.category

import com.only4.cap4k.ddd.core.application.RequestParam

object GetCategoryTreeQry {

    class Request : RequestParam<Response>

    data class Response(
        val items: List<CategoryItem>
    ) {
        data class CategoryItem(
            val categoryId: Long,
            val code: String,
            val name: String,
            val parentId: Long,
            val icon: String?,
            val background: String?,
            val sort: Int,
            val children: List<Children>
        )
        data class Children(
            val categoryId: Long,
            val code: String,
            val name: String,
            val parentId: Long,
            val icon: String?,
            val background: String?,
            val sort: Int,
            val children: List<Children>
        )
    }

}
