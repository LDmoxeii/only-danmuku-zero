package edu.only4.danmuku.adapter.application.queries.customer_profile

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.adapter.domain.repositories.CustomerProfileRepository
import edu.only4.danmuku.application.queries.customer_profile.UniqueCustomerProfileNickNameDeletedQry
import edu.only4.danmuku.domain._share.meta.customer_profile.SCustomerProfile
import org.springframework.stereotype.Service

@Service
class UniqueCustomerProfileNickNameDeletedQryHandler(
    private val repository: CustomerProfileRepository,
) : Query<UniqueCustomerProfileNickNameDeletedQry.Request, UniqueCustomerProfileNickNameDeletedQry.Response> {
    override fun exec(request: UniqueCustomerProfileNickNameDeletedQry.Request): UniqueCustomerProfileNickNameDeletedQry.Response {
        val exists = repository.exists(
            SCustomerProfile.specify { schema ->
                schema.all(
                    schema.nickName eq request.nickName,
                    schema.deleted eq request.deleted,
                    schema.id `neq?` request.excludeCustomerProfileId ?: schema.id.isNotNull(),
                )
            }
        )

        return UniqueCustomerProfileNickNameDeletedQry.Response(
            exists = exists
        )
    }
}
