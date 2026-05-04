package edu.only4.danmuku.adapter.portal.api.payload.video

import java.util.UUID

import edu.only4.danmuku.application.queries.video_file.GetVideoFilesByVideoIdQry
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

object GetVideoPList {

    data class Request(
        var videoId: UUID
    )

    data class FileItem(
        var fileId: UUID,
        var videoId: UUID,
        var userId: UUID,
        var fileIndex: Int,
        var fileName: String,
        var fileSize: Long,
        var filePath: String,
        var duration: Int
    )

    @Mapper(componentModel = "default")
    interface Converter {
        fun fromApp(resp: GetVideoFilesByVideoIdQry.Response.FileItem): FileItem

        companion object {
            val INSTANCE: Converter = Mappers.getMapper(Converter::class.java)
        }
    }
}

