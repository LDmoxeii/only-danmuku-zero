package edu.only4.danmuku.adapter.application.queries.customer_profile

import com.only4.cap4k.ddd.core.application.query.PageQuery
import com.only4.cap4k.ddd.core.share.PageData
import edu.only4.danmuku.application.queries.customer_profile.GetCustomerProfilePageQry
import org.springframework.stereotype.Service

/**
 *
 *
 * 本文件由 cap4k pipeline 生成
 */
@Service
class GetCustomerProfilePageQryHandler : PageQuery<GetCustomerProfilePageQry.Request, GetCustomerProfilePageQry.Response> {

    override fun exec(request: GetCustomerProfilePageQry.Request): PageData<GetCustomerProfilePageQry.Response> {
        return PageData.empty(pageSize = request.pageSize, pageNum = request.pageNum)
    }
}
