package edu.only4.danmuku.adapter.portal.api.payload.video_comment

import java.util.UUID

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

/**
 * 加载弹幕列表(分页)接口载荷
 */
object CancelTopComment {

    class Request(
        val commentId: UUID
    )

    class Response

    @Mapper(componentModel = "default")
    interface Converter {

        companion object {
            val INSTANCE: Converter = Mappers.getMapper(Converter::class.java)
        }
    }
}

