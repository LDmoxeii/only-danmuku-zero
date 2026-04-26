package edu.only4.danmuku.adapter.application.queries.video_post_processing

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_post_processing.ListVideoPostProcessingVariantsForEncryptMasterQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class ListVideoPostProcessingVariantsForEncryptMasterQryHandler : Query<ListVideoPostProcessingVariantsForEncryptMasterQry.Request, ListVideoPostProcessingVariantsForEncryptMasterQry.Response> {

    override fun exec(request: ListVideoPostProcessingVariantsForEncryptMasterQry.Request): ListVideoPostProcessingVariantsForEncryptMasterQry.Response {
        return ListVideoPostProcessingVariantsForEncryptMasterQry.Response(
            quality = TODO("set quality"),
            width = TODO("set width"),
            height = TODO("set height"),
            bandwidthBps = TODO("set bandwidthBps"),
            playlistPath = TODO("set playlistPath")
        )
    }
}
