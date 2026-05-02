package edu.only4.danmuku.adapter.portal.api.payload.u_home

import java.util.UUID

import com.only.engine.translation.annotation.Translation
import com.only.engine.translation.translation.EpochSecondToDateStringTranslation
import com.only4.cap4k.ddd.core.share.PageParam
import edu.only4.danmuku.application.queries.customer_action.GetCollectionPageQry
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

/**
 * 加载用户收藏列表接口载荷
 */
object GetCollectionPage {

    data class Request(
        val userId: UUID?
    ) : PageParam()

    data class Response(
        var actionId: UUID,
        var videoId: String,
        var videoUserId: String,
        var commentId: UUID?,
        var actionType: Int,
        var actionCount: Int,
        var userId: String,
        @get:Translation(type = EpochSecondToDateStringTranslation.TYPE, other = "yyyy-MM-dd HH:mm:ss")
        var actionTime: Long,
        var videoName: String,
        var videoCover: String,
    )

    @Mapper(componentModel = "default")
    interface Converter {

        @Mapping(source = "currentUserId", target = "customerId")
        fun toQry(req: Request, currentUserId: UUID): GetCollectionPageQry.Request

        @Mapping(source = "videoId", target = "videoId")
        @Mapping(source = "videoUserId", target = "videoUserId")
        @Mapping(source = "userId", target = "userId")
        fun fromApp(resp: GetCollectionPageQry.Response.ActionItem): Response

        companion object { val INSTANCE: Converter = Mappers.getMapper(Converter::class.java) }
    }
}

