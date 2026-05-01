package edu.only4.danmuku.adapter.application.queries.video_encrypt

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_encrypt.ListVideoHlsKeyTokensByPostFileQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class ListVideoHlsKeyTokensByPostFileQryHandler : Query<ListVideoHlsKeyTokensByPostFileQry.Request, ListVideoHlsKeyTokensByPostFileQry.Response> {

    override fun exec(request: ListVideoHlsKeyTokensByPostFileQry.Request): ListVideoHlsKeyTokensByPostFileQry.Response {
        return ListVideoHlsKeyTokensByPostFileQry.Response(
            tokenId = TODO("set tokenId")
        )
    }
}
