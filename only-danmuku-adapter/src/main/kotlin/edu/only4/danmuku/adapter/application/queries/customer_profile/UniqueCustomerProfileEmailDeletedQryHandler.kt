package edu.only4.danmuku.adapter.application.queries.customer_profile

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.adapter.domain.repositories.CustomerProfileRepository
import edu.only4.danmuku.application.queries.customer_profile.UniqueCustomerProfileEmailDeletedQry
import edu.only4.danmuku.domain._share.meta.customer_profile.SCustomerProfile
import org.springframework.stereotype.Service

@Service
class UniqueCustomerProfileEmailDeletedQryHandler(
    private val repository: CustomerProfileRepository,
) : Query<UniqueCustomerProfileEmailDeletedQry.Request, UniqueCustomerProfileEmailDeletedQry.Response> {
    override fun exec(request: UniqueCustomerProfileEmailDeletedQry.Request): UniqueCustomerProfileEmailDeletedQry.Response {
        val exists = repository.exists(
            SCustomerProfile.specify { schema ->
                schema.all(
                    schema.email eq request.email,
                    schema.deleted eq request.deleted,
                    schema.id `neq?` request.excludeCustomerProfileId ?: schema.id.isNotNull(),
                )
            }
        )

        return UniqueCustomerProfileEmailDeletedQry.Response(
            exists = exists
        )
    }
}
