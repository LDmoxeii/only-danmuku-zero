package edu.only4.danmuku.adapter.application.queries.video_post_processing

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.VideoHlsEncryptKey
import edu.only4.danmuku.application.queries._share.model.VideoPostProcessingFile
import edu.only4.danmuku.application.queries._share.model.VideoPostProcessingVariant
import edu.only4.danmuku.application.queries._share.model.fetchBy
import edu.only4.danmuku.application.queries._share.model.parent
import edu.only4.danmuku.application.queries._share.model.videoPostId
import edu.only4.danmuku.application.queries.video_post_processing.ListVideoPostProcessingFilesForSyncQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

/**
 * 查询处理完成后的文件清单与产物摘要
 */
@Service
class ListVideoPostProcessingFilesForSyncQryHandler(
    private val sqlClient: KSqlClient,
) : Query<ListVideoPostProcessingFilesForSyncQry.Request, ListVideoPostProcessingFilesForSyncQry.Response> {

    override fun exec(request: ListVideoPostProcessingFilesForSyncQry.Request): ListVideoPostProcessingFilesForSyncQry.Response {
        val files = sqlClient.createQuery(VideoPostProcessingFile::class) {
            where(table.parent.videoPostId eq request.videoPostId)
            select(table.fetchBy {
                fileIndex()
                transcodeOutputPrefix()
                encryptOutputPrefix()
                duration()
                fileSize()
                encryptMethod()
                encryptKeyVersion()
            })
        }.execute()
        val variants = sqlClient.createQuery(VideoPostProcessingVariant::class) {
            where(table.parent.parent.videoPostId eq request.videoPostId)
            select(table.fetchBy {
                quality()
                width()
                height()
                videoBitrateKbps()
                audioBitrateKbps()
                bandwidthBps()
                playlistPath()
                segmentPrefix()
                segmentDuration()
                parent {
                    fileIndex()
                }
            })
        }.execute()
        val variantMap = variants.groupBy { it.parent.fileIndex }
        val keys = sqlClient.createQuery(VideoHlsEncryptKey::class) {
            where(table.videoPostId eq request.videoPostId)
            select(table)
        }.execute()
        val keyMap = keys.groupBy { it.fileIndex }.mapValues { entry ->
            entry.value.maxByOrNull { it.keyVersion }
        }

        return ListVideoPostProcessingFilesForSyncQry.Response(
            items = files.map { file ->
                val key = keyMap[file.fileIndex]
                ListVideoPostProcessingFilesForSyncQry.Response.FileItem(
                    fileIndex = file.fileIndex,
                    transcodeOutputPrefix = file.transcodeOutputPrefix,
                    encryptOutputPrefix = file.encryptOutputPrefix,
                    variants = variantMap[file.fileIndex]
                        ?.sortedWith(
                            compareByDescending<VideoPostProcessingVariant> { it.bandwidthBps }
                                .thenBy { it.quality }
                        )
                        ?.map { variant ->
                            ListVideoPostProcessingFilesForSyncQry.Response.VariantItem(
                                quality = variant.quality,
                                width = variant.width,
                                height = variant.height,
                                videoBitrateKbps = variant.videoBitrateKbps,
                                audioBitrateKbps = variant.audioBitrateKbps,
                                bandwidthBps = variant.bandwidthBps,
                                playlistPath = variant.playlistPath,
                                segmentPrefix = variant.segmentPrefix,
                                segmentDuration = variant.segmentDuration
                            )
                        } ?: emptyList(),
                    duration = file.duration,
                    fileSize = file.fileSize,
                    encryptMethod = key?.method?.name ?: file.encryptMethod.name,
                    keyVersion = key?.keyVersion ?: file.encryptKeyVersion
                )
            }
        )
    }
}
