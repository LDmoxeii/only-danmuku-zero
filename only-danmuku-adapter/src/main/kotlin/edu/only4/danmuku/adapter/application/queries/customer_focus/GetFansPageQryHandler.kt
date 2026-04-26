package edu.only4.danmuku.adapter.application.queries.customer_focus

import com.only4.cap4k.ddd.core.application.query.PageQuery
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.application.queries.customer_focus.GetFansPageQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetFansPageQryHandler : PageQuery<GetFansPageQry.Request, GetFansPageQry.Response> {

    override fun exec(request: GetFansPageQry.Request): PageData<GetFansPageQry.Response> {
        return PageData.create(request, 1L, listOf(
            GetFansPageQry.Response(
                userId = TODO("set userId"),
                nickName = TODO("set nickName"),
                avatar = TODO("set avatar"),
                personIntroduction = TODO("set personIntroduction"),
                fansCount = TODO("set fansCount"),
                haveFocus = TODO("set haveFocus"),
                focusType = TODO("set focusType")
            )
        ))
    }
}
