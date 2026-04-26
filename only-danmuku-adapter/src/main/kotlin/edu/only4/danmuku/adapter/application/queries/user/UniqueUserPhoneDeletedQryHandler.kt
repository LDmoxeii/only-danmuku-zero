package edu.only4.danmuku.adapter.application.queries.user

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.adapter.domain.repositories.UserRepository
import edu.only4.danmuku.application.queries.user.UniqueUserPhoneDeletedQry
import edu.only4.danmuku.domain._share.meta.user.SUser
import org.springframework.stereotype.Service

@Service
class UniqueUserPhoneDeletedQryHandler(
    private val repository: UserRepository,
) : Query<UniqueUserPhoneDeletedQry.Request, UniqueUserPhoneDeletedQry.Response> {
    override fun exec(request: UniqueUserPhoneDeletedQry.Request): UniqueUserPhoneDeletedQry.Response {
        val exists = repository.exists(
            SUser.specify { schema ->
                schema.all(
                    schema.phone eq request.phone,
                    schema.deleted eq request.deleted,
                    schema.id `neq?` request.excludeUserId ?: schema.id.isNotNull(),
                )
            }
        )

        return UniqueUserPhoneDeletedQry.Response(
            exists = exists
        )
    }
}
