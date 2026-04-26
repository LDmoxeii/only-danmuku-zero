package edu.only4.danmuku.adapter.portal.api.payload.admin_category

object UpdateCategory {

    data class Request(
        val pCategoryId: Long,
        val categoryId: Long,
        val categoryCode: String,
        val categoryName: String,
        val icon: String?,
        val background: String?
    )

    class Response

}
