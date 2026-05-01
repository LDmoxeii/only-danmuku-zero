package edu.only4.danmuku.adapter.application.queries.video_transcode

import com.only.engine.json.misc.JsonUtils
import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.VideoFilePostVariant
import edu.only4.danmuku.application.queries._share.model.filePostId
import edu.only4.danmuku.application.queries.video_transcode.ListVideoAbrVariantsQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

/**
 * 查询指定 fileId 的可用清晰度档位列表
 */
@Service
class ListVideoAbrVariantsQryHandler(
    private val sqlClient: KSqlClient,
) : Query<ListVideoAbrVariantsQry.Request, ListVideoAbrVariantsQry.Response> {

    override fun exec(request: ListVideoAbrVariantsQry.Request): ListVideoAbrVariantsQry.Response {
        val variants = sqlClient.createQuery(VideoFilePostVariant::class) {
            where(table.filePostId eq request.fileId)
            select(table)
        }.execute().sortedWith(
            compareByDescending<VideoFilePostVariant> { qualityScore(it.quality) }
                .thenBy { it.quality }
        )

        return ListVideoAbrVariantsQry.Response(
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
