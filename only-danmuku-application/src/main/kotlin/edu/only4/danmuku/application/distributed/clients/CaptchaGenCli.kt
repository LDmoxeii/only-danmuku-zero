package edu.only4.danmuku.application.distributed.clients

import com.only.engine.enums.CaptchaChannel
import com.only4.cap4k.ddd.core.application.RequestParam

object CaptchaGenCli {

    data class Request(
        val bizType: String,
        val channel: CaptchaChannel = CaptchaChannel.INLINE,
        val targets: List<String> = emptyList(),
        val templateCode: String? = null
    ) : RequestParam<Response>

    data class Response(
        val captchaId: String,
        val byte: String,
        val text: String
    )

}
