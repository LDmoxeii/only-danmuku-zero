package edu.only4.danmuku.adapter.application.queries.video_file

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.VideoFile
import edu.only4.danmuku.application.queries._share.model.fetchBy
import edu.only4.danmuku.application.queries._share.model.fileIndex
import edu.only4.danmuku.application.queries._share.model.id
import edu.only4.danmuku.application.queries._share.model.video
import edu.only4.danmuku.application.queries.video_file.GetVideoFilesByVideoIdQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.asc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

/**
 * 根据视频ID获取文件列表
 */
@Service
class GetVideoFilesByVideoIdQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetVideoFilesByVideoIdQry.Request, GetVideoFilesByVideoIdQry.Response> {

    override fun exec(request: GetVideoFilesByVideoIdQry.Request): GetVideoFilesByVideoIdQry.Response {
        val fileList = sqlClient.createQuery(VideoFile::class) {
            where(table.video.id eq request.videoId)
            orderBy(table.fileIndex.asc())
            select(table.fetchBy {
                allScalarFields()
                video()
                customer()
            })
        }.execute()

        return GetVideoFilesByVideoIdQry.Response(
            items = fileList.map { file ->
                GetVideoFilesByVideoIdQry.Response.FileItem(
                    fileId = file.id,
                    videoId = file.video.id,
                    userId = file.customer.id,
                    fileIndex = file.fileIndex,
                    fileName = file.fileName ?: "",
                    fileSize = file.fileSize ?: 0L,
                    filePath = file.filePath ?: "",
                    duration = file.duration ?: 0
                )
            }
        )
    }
}
