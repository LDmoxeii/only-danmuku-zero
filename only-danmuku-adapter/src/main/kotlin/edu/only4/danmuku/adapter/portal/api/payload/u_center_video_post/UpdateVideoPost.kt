
package edu.only4.danmuku.adapter.portal.api.payload.u_center_video_post

import edu.only4.danmuku.domain._share.enums.PostType

object UpdateVideoPost {

    data class Request(
        val videoPostId: Long,
        val videoCover: String,
        val videoName: String,
        val parentCategoryId: Long,
        val categoryId: Long?,
        val postType: PostType,
        val originInfo: String?,
        val tags: String,
        val introduction: String?,
        val interaction: String?,
        val uploadFileList: String
    )

    class Response

}
