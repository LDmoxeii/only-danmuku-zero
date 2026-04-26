package edu.only4.danmuku.adapter.application.queries.customer_profile

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.adapter.domain.repositories.CustomerProfileRepository
import edu.only4.danmuku.application.queries.customer_profile.UniqueCustomerProfilePhoneDeletedQry
import edu.only4.danmuku.domain._share.meta.customer_profile.SCustomerProfile
import org.springframework.stereotype.Service

@Service
class UniqueCustomerProfilePhoneDeletedQryHandler(
    private val repository: CustomerProfileRepository,
) : Query<UniqueCustomerProfilePhoneDeletedQry.Request, UniqueCustomerProfilePhoneDeletedQry.Response> {
    override fun exec(request: UniqueCustomerProfilePhoneDeletedQry.Request): UniqueCustomerProfilePhoneDeletedQry.Response {
        val exists = repository.exists(
            SCustomerProfile.specify { schema ->
                schema.all(
                    schema.phone eq request.phone,
                    schema.deleted eq request.deleted,
                    schema.id `neq?` request.excludeCustomerProfileId ?: schema.id.isNotNull(),
                )
            }
        )

        return UniqueCustomerProfilePhoneDeletedQry.Response(
            exists = exists
        )
    }
}
