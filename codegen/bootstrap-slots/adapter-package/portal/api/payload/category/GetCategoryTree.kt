package {{ basePackage }}.adapter.portal.api.payload.category

/**
 * 加载所有分类接口载荷
 */
object GetCategoryTree {

    class Request

    data class Response(
        var categoryId: Long,
        var categoryCode: String,
        var categoryName: String,
        var parentCategoryId: Long,
        var icon: String?,
        var background: String?,
        var sort: Int,
        var children: List<Response>,
    )
}
