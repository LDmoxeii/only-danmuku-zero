package edu.only4.danmuku.adapter.application.distributed.clients

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.DeleteVideoFileResourcesCli
import org.springframework.stereotype.Service

@Service
class DeleteVideoFileResourcesCliHandler : RequestHandler<DeleteVideoFileResourcesCli.Request, DeleteVideoFileResourcesCli.Response> {

    override fun exec(request: DeleteVideoFileResourcesCli.Request): DeleteVideoFileResourcesCli.Response {
        return DeleteVideoFileResourcesCli.Response
    }
}
