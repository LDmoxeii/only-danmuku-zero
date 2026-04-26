package edu.only4.danmuku.adapter.application.distributed.clients

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.RefreshLoginSessionCli
import org.springframework.stereotype.Service

@Service
class RefreshLoginSessionCliHandler : RequestHandler<RefreshLoginSessionCli.Request, RefreshLoginSessionCli.Response> {

    override fun exec(request: RefreshLoginSessionCli.Request): RefreshLoginSessionCli.Response {
        return RefreshLoginSessionCli.Response
    }
}
