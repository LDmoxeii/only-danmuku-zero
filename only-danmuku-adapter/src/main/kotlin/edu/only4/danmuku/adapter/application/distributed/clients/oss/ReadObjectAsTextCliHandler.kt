package edu.only4.danmuku.adapter.application.distributed.clients.oss

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.oss.ReadObjectAsTextCli
import org.springframework.stereotype.Service

@Service
class ReadObjectAsTextCliHandler : RequestHandler<ReadObjectAsTextCli.Request, ReadObjectAsTextCli.Response> {

    override fun exec(request: ReadObjectAsTextCli.Request): ReadObjectAsTextCli.Response {
        return ReadObjectAsTextCli.Response(
            content = TODO("set content")
        )
    }
}
