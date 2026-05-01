
package edu.only4.danmuku.application.queries.video_play_history

import com.only4.cap4k.ddd.core.application.RequestParam

object GetUserPlayHistoryQry {

    data class Request(
        val customerId: Long
    ) : RequestParam<Response>

    data object Response

}
