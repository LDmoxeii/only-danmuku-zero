package edu.only4.danmuku.adapter.application.queries.video

import com.only.engine.json.misc.JsonUtils
import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.VideoFileVariant
import edu.only4.danmuku.application.queries._share.model.fileId
import edu.only4.danmuku.application.queries.video.ListVideoFileVariantsQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

/**
 * 查询视频文件清晰度档位
 */
@Service
class ListVideoFileVariantsQryHandler(
    private val sqlClient: KSqlClient,
) : Query<ListVideoFileVariantsQry.Request, ListVideoFileVariantsQry.Response> {

    override fun exec(request: ListVideoFileVariantsQry.Request): ListVideoFileVariantsQry.Response {
        val variants = sqlClient.createQuery(VideoFileVariant::class) {
            where(table.fileId eq request.fileId)
            select(table)
        }.execute().sortedWith(
            compareByDescending<VideoFileVariant> { qualityScore(it.quality) }
                .thenBy { it.quality }
        )

        return ListVideoFileVariantsQry.Response(
            qualities = variants.map { it.quality },
            variantJson = JsonUtils.toJsonString(variants) ?: "[]"
        )
    }

    private fun qualityScore(quality: String): Int {
        val number = QUALITY_NUMBER_REGEX.find(quality)?.groupValues?.getOrNull(1)?.toIntOrNull()
        return number ?: Int.MIN_VALUE
    }

    companion object {
        private val QUALITY_NUMBER_REGEX = Regex("(\\d+)")
    }
}
