package edu.only4.danmuku.adapter.application.queries.video_hls_encrypt_key

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_hls_encrypt_key.UniqueVideoHlsEncryptKeyQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class UniqueVideoHlsEncryptKeyQryHandler : Query<UniqueVideoHlsEncryptKeyQry.Request, UniqueVideoHlsEncryptKeyQry.Response> {

    override fun exec(request: UniqueVideoHlsEncryptKeyQry.Request): UniqueVideoHlsEncryptKeyQry.Response {
        return UniqueVideoHlsEncryptKeyQry.Response(
            exists = TODO("set exists")
        )
    }
}
