package edu.only4.danmuku.adapter.application.distributed.clients

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.CaptchaGenCli
import org.springframework.stereotype.Service

@Service
class CaptchaGenCliHandler : RequestHandler<CaptchaGenCli.Request, CaptchaGenCli.Response> {

    override fun exec(request: CaptchaGenCli.Request): CaptchaGenCli.Response {
        return CaptchaGenCli.Response(
            captchaId = TODO("set captchaId"),
            byte = TODO("set byte"),
            text = TODO("set text")
        )
    }
}
