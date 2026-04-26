package edu.only4.danmuku.adapter.application.distributed.clients.statistics

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.statistics.ReportVideoPlayOnlineCli
import org.springframework.stereotype.Service

@Service
class ReportVideoPlayOnlineCliHandler : RequestHandler<ReportVideoPlayOnlineCli.Request, ReportVideoPlayOnlineCli.Response> {

    override fun exec(request: ReportVideoPlayOnlineCli.Request): ReportVideoPlayOnlineCli.Response {
        return ReportVideoPlayOnlineCli.Response(
            current = TODO("set current")
        )
    }
}
