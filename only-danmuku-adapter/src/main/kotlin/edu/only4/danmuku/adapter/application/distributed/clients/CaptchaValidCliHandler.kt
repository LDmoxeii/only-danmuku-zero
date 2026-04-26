package edu.only4.danmuku.adapter.application.distributed.clients

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.CaptchaValidCli
import org.springframework.stereotype.Service

@Service
class CaptchaValidCliHandler : RequestHandler<CaptchaValidCli.Request, CaptchaValidCli.Response> {

    override fun exec(request: CaptchaValidCli.Request): CaptchaValidCli.Response {
        return CaptchaValidCli.Response(
            result = TODO("set result")
        )
    }
}
