
package edu.only4.danmuku.adapter.portal.api.payload.admin_category

object GetCategoryTree {

    class Request

    data class Response(
        val categoryId: Long,
        val categoryCode: String,
        val categoryName: String,
        val parentId: Long?,
        val icon: String?,
        val background: String?,
        val sort: Int,
        val children: List<Children>
    ) {
        data class Children(
            val categoryId: Long,
            val categoryCode: String,
            val categoryName: String,
            val parentId: Long?,
            val icon: String?,
            val background: String?,
            val sort: Int,
            val children: List<Children>
        )
    }

}
