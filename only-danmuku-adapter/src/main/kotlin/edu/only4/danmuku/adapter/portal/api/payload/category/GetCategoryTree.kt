
package edu.only4.danmuku.adapter.portal.api.payload.category

object GetCategoryTree {

    class Request

    data class Response(
        val categoryId: Long,
        val categoryCode: String,
        val categoryName: String,
        val parentCategoryId: Long,
        val icon: String?,
        val background: String?,
        val sort: Int,
        val children: List<Children>
    ) {
        data class Children(
            val categoryId: Long,
            val categoryCode: String,
            val categoryName: String,
            val parentCategoryId: Long,
            val icon: String?,
            val background: String?,
            val sort: Int,
            val children: List<Children>
        )
    }

}
