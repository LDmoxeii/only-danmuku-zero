package edu.only4.danmuku.adapter.application.queries.video_post_processing

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.VideoPostProcessingVariant
import edu.only4.danmuku.application.queries._share.model.encryptStatus
import edu.only4.danmuku.application.queries._share.model.fetchBy
import edu.only4.danmuku.application.queries._share.model.fileIndex
import edu.only4.danmuku.application.queries._share.model.parent
import edu.only4.danmuku.application.queries._share.model.videoPostId
import edu.only4.danmuku.application.queries.video_post_processing.ListVideoPostProcessingVariantsForEncryptMasterQry
import edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

/**
 * 查询某分P的清晰度档位元数据
 */
@Service
class ListVideoPostProcessingVariantsForEncryptMasterQryHandler(
    private val sqlClient: KSqlClient,
) : Query<ListVideoPostProcessingVariantsForEncryptMasterQry.Request, ListVideoPostProcessingVariantsForEncryptMasterQry.Response> {

    override fun exec(request: ListVideoPostProcessingVariantsForEncryptMasterQry.Request): ListVideoPostProcessingVariantsForEncryptMasterQry.Response {
        val variants = sqlClient.createQuery(VideoPostProcessingVariant::class) {
            where(table.parent.parent.videoPostId eq request.videoPostId)
            where(table.parent.fileIndex eq request.fileIndex)
            where(table.encryptStatus eq ProcessStatus.SUCCESS)
            select(table.fetchBy {
                quality()
                width()
                height()
                bandwidthBps()
                playlistPath()
            })
        }.execute()

        return ListVideoPostProcessingVariantsForEncryptMasterQry.Response(
            items = variants.sortedWith(
                compareByDescending<VideoPostProcessingVariant> { it.bandwidthBps }
                    .thenBy { it.quality }
            ).map { variant ->
                ListVideoPostProcessingVariantsForEncryptMasterQry.Response.VariantItem(
                    quality = variant.quality,
                    width = variant.width,
                    height = variant.height,
                    bandwidthBps = variant.bandwidthBps,
                    playlistPath = variant.playlistPath
                )
            }
        )
    }
}
