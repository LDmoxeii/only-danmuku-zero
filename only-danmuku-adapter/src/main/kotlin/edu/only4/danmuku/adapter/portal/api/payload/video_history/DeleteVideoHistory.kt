package edu.only4.danmuku.adapter.portal.api.payload.video_history

import java.util.UUID

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

object DeleteVideoHistory {

    class Request(
        val videoId: UUID
    )

    class Response

    @Mapper(componentModel = "default")
    interface Converter {

        companion object {
            val INSTANCE: Converter = Mappers.getMapper(Converter::class.java)
        }
    }
}

