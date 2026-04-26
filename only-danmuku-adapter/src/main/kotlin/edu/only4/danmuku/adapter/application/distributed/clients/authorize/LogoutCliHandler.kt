package edu.only4.danmuku.adapter.application.distributed.clients.authorize

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.authorize.LogoutCli
import org.springframework.stereotype.Service

@Service
class LogoutCliHandler : RequestHandler<LogoutCli.Request, LogoutCli.Response> {

    override fun exec(request: LogoutCli.Request): LogoutCli.Response {
        return LogoutCli.Response
    }
}
