package edu.only4.danmuku.adapter.portal.api.payload.video

import java.util.UUID

import com.only.engine.translation.annotation.Translation
import com.only.engine.translation.translation.EpochSecondToDateStringTranslation
import edu.only4.danmuku.application.queries.customer_action.GetUserActionsByVideoIdQry
import edu.only4.danmuku.application.queries.video.GetVideoInfoQry
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

object GetVideoDetail {

    data class Request(
        var videoId: UUID
    )

    data class Response(
        var videoInfo: VideoInfo? = null,
        var userActionList: List<UserAction>? = null
    )

    data class VideoInfo(
        var videoId: UUID,
        var videoCover: String,
        var videoName: String,
        var userId: UUID,
        @get:Translation(type = EpochSecondToDateStringTranslation.TYPE, other = "yyyy-MM-dd HH:mm:ss")
        var createTime: Long,
        var postType: Int,
        var originInfo: String?,
        var tags: String?,
        var introduction: String? = null,
        var interaction: String? = null,
        var playCount: Int? = null,
        var likeCount: Int? = null,
        var danmukuCount: Int? = null,
        var commentCount: Int? = null,
        var coinCount: Int? = null,
        var collectCount: Int? = null
    )

    data class UserAction(
        var actionId: UUID,
        var userId: UUID,
        var videoId: UUID,
        var videoName: String,
        var videoCover: String,
        var videoUserId: UUID,
        var commentId: UUID?,
        var actionType: Int? = null,
        var actionCount: Int? = null,
        @get:Translation(type = EpochSecondToDateStringTranslation.TYPE, other = "yyyy-MM-dd HH:mm:ss")
        var cationTime: Long,
    )

    @Mapper(componentModel = "default")
    interface Converter {
        fun fromApp(info: GetVideoInfoQry.Response): VideoInfo

        @Mapping(source = "actionTime", target = "cationTime")
        fun fromApp(action: GetUserActionsByVideoIdQry.Response.ActionItem): UserAction

        companion object { val INSTANCE: Converter = Mappers.getMapper(Converter::class.java) }
    }
}

