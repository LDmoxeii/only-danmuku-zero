package edu.only4.danmuku.adapter.application.distributed.clients

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.CleanTempFilesCli
import org.springframework.stereotype.Service

@Service
class CleanTempFilesCliHandler : RequestHandler<CleanTempFilesCli.Request, CleanTempFilesCli.Response> {

    override fun exec(request: CleanTempFilesCli.Request): CleanTempFilesCli.Response {
        return CleanTempFilesCli.Response
    }
}
