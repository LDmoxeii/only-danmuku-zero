package edu.only4.danmuku.adapter.application.queries.video_encrypt

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_encrypt.ListVideoHlsEncryptKeysByPostFileQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class ListVideoHlsEncryptKeysByPostFileQryHandler : Query<ListVideoHlsEncryptKeysByPostFileQry.Request, ListVideoHlsEncryptKeysByPostFileQry.Response> {

    override fun exec(request: ListVideoHlsEncryptKeysByPostFileQry.Request): ListVideoHlsEncryptKeysByPostFileQry.Response {
        return ListVideoHlsEncryptKeysByPostFileQry.Response(
            encryptKeyId = TODO("set encryptKeyId")
        )
    }
}
