package edu.only4.danmuku.adapter.application.queries.user

import com.only4.cap4k.ddd.core.application.query.Query
import edu.only4.danmuku.adapter.domain.repositories.UserRepository
import edu.only4.danmuku.application.queries.user.UniqueUserEmailDeletedQry
import edu.only4.danmuku.domain._share.meta.user.SUser
import org.springframework.stereotype.Service

@Service
class UniqueUserEmailDeletedQryHandler(
    private val repository: UserRepository,
) : Query<UniqueUserEmailDeletedQry.Request, UniqueUserEmailDeletedQry.Response> {
    override fun exec(request: UniqueUserEmailDeletedQry.Request): UniqueUserEmailDeletedQry.Response {
        val exists = repository.exists(
            SUser.specify { schema ->
                schema.all(
                    schema.email eq request.email,
                    schema.deleted eq request.deleted,
                    schema.id `neq?` request.excludeUserId ?: schema.id.isNotNull(),
                )
            }
        )

        return UniqueUserEmailDeletedQry.Response(
            exists = exists
        )
    }
}
