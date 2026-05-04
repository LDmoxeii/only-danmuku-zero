package edu.only4.danmuku.adapter.portal.api.payload.admin_category

import java.util.UUID

object ChangeCategorySort {

    data class Request(
        val parentId: UUID,
        val categoryIds: String,
    )
}

