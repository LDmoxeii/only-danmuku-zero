package edu.only4.danmuku.adapter.application.queries.video_encrypt

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_encrypt.GetVideoEncryptStatusQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetVideoEncryptStatusQryHandler : Query<GetVideoEncryptStatusQry.Request, GetVideoEncryptStatusQry.Response> {

    override fun exec(request: GetVideoEncryptStatusQry.Request): GetVideoEncryptStatusQry.Response {
        return GetVideoEncryptStatusQry.Response(
            encryptStatus = TODO("set encryptStatus"),
            encryptMethod = TODO("set encryptMethod"),
            keyId = TODO("set keyId"),
            keyVersion = TODO("set keyVersion"),
            keyQuality = TODO("set keyQuality"),
            encryptedMasterPath = TODO("set encryptedMasterPath")
        )
    }
}
