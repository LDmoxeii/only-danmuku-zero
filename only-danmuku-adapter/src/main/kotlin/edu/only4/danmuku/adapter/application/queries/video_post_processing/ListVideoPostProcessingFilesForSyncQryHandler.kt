package edu.only4.danmuku.adapter.application.queries.video_post_processing

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_post_processing.ListVideoPostProcessingFilesForSyncQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class ListVideoPostProcessingFilesForSyncQryHandler : Query<ListVideoPostProcessingFilesForSyncQry.Request, ListVideoPostProcessingFilesForSyncQry.Response> {

    override fun exec(request: ListVideoPostProcessingFilesForSyncQry.Request): ListVideoPostProcessingFilesForSyncQry.Response {
        return ListVideoPostProcessingFilesForSyncQry.Response(
            fileIndex = TODO("set fileIndex"),
            transcodeOutputPrefix = TODO("set transcodeOutputPrefix"),
            encryptOutputPrefix = TODO("set encryptOutputPrefix"),
            variants = TODO("set variants"),
            duration = TODO("set duration"),
            fileSize = TODO("set fileSize"),
            encryptMethod = TODO("set encryptMethod"),
            keyVersion = TODO("set keyVersion")
        )
    }
}
