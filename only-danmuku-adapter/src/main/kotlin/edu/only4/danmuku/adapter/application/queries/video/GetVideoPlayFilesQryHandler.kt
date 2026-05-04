package edu.only4.danmuku.adapter.application.queries.video

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.VideoFilePost
import edu.only4.danmuku.application.queries._share.model.fetchBy
import edu.only4.danmuku.application.queries._share.model.fileIndex
import edu.only4.danmuku.application.queries._share.model.videoPostId
import edu.only4.danmuku.application.queries.video.GetVideoPlayFilesQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.asc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

/**
 * 获取视频播放文件列表
 */
@Service
class GetVideoPlayFilesQryHandler(
    private val sqlClient: KSqlClient,
) : Query<GetVideoPlayFilesQry.Request, GetVideoPlayFilesQry.Response> {

    override fun exec(request: GetVideoPlayFilesQry.Request): GetVideoPlayFilesQry.Response {
        val files = sqlClient.createQuery(VideoFilePost::class) {
            where(table.videoPostId eq request.videoId)
            orderBy(table.fileIndex.asc())
            select(table.fetchBy {
                allScalarFields()
                videoPost()
            })
        }.execute()

        return GetVideoPlayFilesQry.Response(
            items = files.map { file ->
                GetVideoPlayFilesQry.Response.FileItem(
                    fileId = file.id,
                    videoId = file.videoPost.id,
                    fileIndex = file.fileIndex,
                    fileName = file.fileName,
                    fileSize = file.fileSize,
                    filePath = file.transcodeOutputPrefix,
                    duration = file.duration
                )
            }
        )
    }
}
