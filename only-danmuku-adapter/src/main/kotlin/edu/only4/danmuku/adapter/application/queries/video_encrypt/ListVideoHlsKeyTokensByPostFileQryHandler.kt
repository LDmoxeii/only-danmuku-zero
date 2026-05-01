package edu.only4.danmuku.adapter.application.queries.video_encrypt

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries._share.model.VideoHlsKeyToken
import edu.only4.danmuku.application.queries._share.model.fileIndex
import edu.only4.danmuku.application.queries._share.model.id
import edu.only4.danmuku.application.queries._share.model.videoPostId
import edu.only4.danmuku.application.queries.video_encrypt.ListVideoHlsKeyTokensByPostFileQry
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Service

/**
 * 按 videoPostId + fileIndex 查询 token ID 列表
 */
@Service
class ListVideoHlsKeyTokensByPostFileQryHandler(
    private val sqlClient: KSqlClient,
) : Query<ListVideoHlsKeyTokensByPostFileQry.Request, ListVideoHlsKeyTokensByPostFileQry.Response> {

    override fun exec(request: ListVideoHlsKeyTokensByPostFileQry.Request): ListVideoHlsKeyTokensByPostFileQry.Response {
        val ids = sqlClient.createQuery(VideoHlsKeyToken::class) {
            where(table.videoPostId eq request.videoPostId)
            where(table.fileIndex eq request.fileIndex)
            select(table.id)
        }.execute()

        return ListVideoHlsKeyTokensByPostFileQry.Response(
            items = ids.map {
                ListVideoHlsKeyTokensByPostFileQry.Response.TokenItem(
                    tokenId = it
                )
            }
        )
    }
}
