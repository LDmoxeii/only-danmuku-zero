package edu.only4.danmuku.adapter.application.queries.video_encrypt

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_encrypt.ListVideoHlsEncryptKeysQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class ListVideoHlsEncryptKeysQryHandler : Query<ListVideoHlsEncryptKeysQry.Request, ListVideoHlsEncryptKeysQry.Response> {

    override fun exec(request: ListVideoHlsEncryptKeysQry.Request): ListVideoHlsEncryptKeysQry.Response {
        return ListVideoHlsEncryptKeysQry.Response(
            keysJson = TODO("set keysJson")
        )
    }
}
