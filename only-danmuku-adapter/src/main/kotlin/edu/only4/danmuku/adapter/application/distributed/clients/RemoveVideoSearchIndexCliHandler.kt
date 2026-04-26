package edu.only4.danmuku.adapter.application.distributed.clients

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.RemoveVideoSearchIndexCli
import org.springframework.stereotype.Service

@Service
class RemoveVideoSearchIndexCliHandler : RequestHandler<RemoveVideoSearchIndexCli.Request, RemoveVideoSearchIndexCli.Response> {

    override fun exec(request: RemoveVideoSearchIndexCli.Request): RemoveVideoSearchIndexCli.Response {
        return RemoveVideoSearchIndexCli.Response
    }
}
