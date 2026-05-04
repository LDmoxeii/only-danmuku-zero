package edu.only4.danmuku.adapter.portal.api.payload.video

import java.util.UUID

import edu.only4.danmuku.application.queries.video.GetVideoPageQry
import jakarta.validation.constraints.NotEmpty
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

/**
 * 获取推荐视频(基于关键词)接口载荷
 */
object GetVideoRecommendList {

    /**
     * 请求参数
     */
    data class Request(
        /** 关键词 */
        @field:NotEmpty(message = "关键词不能为空")
        val keyword: String = "",
        /** 当前视频ID(排除) */
        val videoId: UUID
    )

    /**
     * 响应结果 - 推荐视频列表
     */
    data class Response(
        /** 推荐视频列表 */
        var list: List<Item>? = null
    )

    data class Item(
        var videoId: UUID,
        var videoCover: String?,
        var videoName: String?,
        var userId: UUID?,
        var createTime: Long,
        var lastUpdateTime: Long?,
        var parentCategoryId: UUID,
        var categoryId: UUID?,
        var postType: Int,
        var originInfo: String?,
        var tags: String?,
        var introduction: String?,
        var duration: Int,
        val status: Int,
        var playCount: Int,
        var likeCount: Int,
        var danmukuCount: Int,
        var commentCount: Int,
        var coinCount: Int,
        var collectCount: Int,
        var recommendType: Int,
        var lastPlayTime: Long?,
        var nickName: String? = null,
        var avatar: String? = null,
        var categoryFullName: String?,
    )

    @Mapper(componentModel = "default",
        imports = [List::class]
    )
    interface Converter {

        @Mapping(target = "videoNameFuzzy", source = "request.keyword")
        @Mapping(target = "excludeVideoIds", expression = "java(List.of(request.getVideoId()))")
        @Mapping(target = "pageNum", constant = "1")
        @Mapping(target = "pageSize", constant = "10")
        fun toQry(request: Request): GetVideoPageQry.Request

        fun fromQry(resp: GetVideoPageQry.Response.VideoItem): Item

        companion object { val INSTANCE: Converter = Mappers.getMapper(Converter::class.java) }
    }
}

