package edu.only4.danmuku.adapter.application.distributed.clients.statistics

import com.only4.cap4k.ddd.core.application.RequestHandler
import edu.only4.danmuku.application.distributed.clients.statistics.ReportVideoSearchCountCli
import org.springframework.stereotype.Service

@Service
class ReportVideoSearchCountCliHandler : RequestHandler<ReportVideoSearchCountCli.Request, ReportVideoSearchCountCli.Response> {

    override fun exec(request: ReportVideoSearchCountCli.Request): ReportVideoSearchCountCli.Response {
        return ReportVideoSearchCountCli.Response
    }
}
