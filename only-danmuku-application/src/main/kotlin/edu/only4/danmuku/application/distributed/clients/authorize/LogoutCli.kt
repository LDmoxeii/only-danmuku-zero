package edu.only4.danmuku.application.distributed.clients.authorize

import com.only4.cap4k.ddd.core.application.RequestParam

object LogoutCli {

    class Request : RequestParam<Response>

    data object Response

}
