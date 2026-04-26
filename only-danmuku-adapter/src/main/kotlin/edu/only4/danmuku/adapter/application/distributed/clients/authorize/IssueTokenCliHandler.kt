package edu.only4.danmuku.adapter.application.distributed.clients.authorize

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.authorize.IssueTokenCli
import org.springframework.stereotype.Service

@Service
class IssueTokenCliHandler : RequestHandler<IssueTokenCli.Request, IssueTokenCli.Response> {

    override fun exec(request: IssueTokenCli.Request): IssueTokenCli.Response {
        return IssueTokenCli.Response(
            token = TODO("set token")
        )
    }
}
