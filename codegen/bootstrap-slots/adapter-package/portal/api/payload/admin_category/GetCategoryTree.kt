package {{ basePackage }}.adapter.portal.api.payload.admin_category

/**
 * 加载分类列表(树形结构)接口载荷
 */
object GetCategoryTree {

    data class Response(
        var categoryId: Long,
        var categoryCode: String,
        var categoryName: String,
        var parentId: Long?,
        var icon: String?,
        var background: String?,
        var sort: Int,
        var children: List<Response>,
    )
}
