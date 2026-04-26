package edu.only4.danmuku.adapter.application.queries.video_encrypt

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.application.queries.video_encrypt.GetLatestVideoHlsKeyVersionQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetLatestVideoHlsKeyVersionQryHandler : Query<GetLatestVideoHlsKeyVersionQry.Request, GetLatestVideoHlsKeyVersionQry.Response> {

    override fun exec(request: GetLatestVideoHlsKeyVersionQry.Request): GetLatestVideoHlsKeyVersionQry.Response {
        return GetLatestVideoHlsKeyVersionQry.Response(
            keyVersion = TODO("set keyVersion"),
            qualities = TODO("set qualities")
        )
    }
}
