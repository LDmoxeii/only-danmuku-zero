package edu.only4.danmuku.domain._share.meta.user_abnormal_operation_log

import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.user_abnormal_operation_log.UserAbnormalOperationLog
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SUserAbnormalOperationLog(
    private val root: Path<UserAbnormalOperationLog>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val userId = "userId"

        val userType = "userType"

        val opType = "opType"

        val ip = "ip"

        val occurTime = "occurTime"

        val description = "description"

        val extra = "extra"

        val createUserId = "createUserId"

        val createBy = "createBy"

        val createTime = "createTime"

        val updateUserId = "updateUserId"

        val updateBy = "updateBy"

        val updateTime = "updateTime"

        val deleted = "deleted"

    }

    companion object {

        val props = PROPERTY_NAMES()

        @JvmStatic
        fun specify(builder: PredicateBuilder<SUserAbnormalOperationLog>): Specification<UserAbnormalOperationLog> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SUserAbnormalOperationLog>, distinct: Boolean): Specification<UserAbnormalOperationLog> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SUserAbnormalOperationLog>,
            vararg orderBuilders: OrderBuilder<SUserAbnormalOperationLog>,
        ): Specification<UserAbnormalOperationLog> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SUserAbnormalOperationLog>,
            orderBuilders: List<OrderBuilder<SUserAbnormalOperationLog>>,
        ): Specification<UserAbnormalOperationLog> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SUserAbnormalOperationLog>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SUserAbnormalOperationLog>,
        ): Specification<UserAbnormalOperationLog> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SUserAbnormalOperationLog>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SUserAbnormalOperationLog>>,
        ): Specification<UserAbnormalOperationLog> {
            return specify { schema, criteriaQuery, _ ->
                criteriaQuery.where(builder.build(schema))
                criteriaQuery.distinct(distinct)
                if (orderBuilders.isNotEmpty()) {
                    criteriaQuery.orderBy(orderBuilders.map { it.build(schema) })
                }
                null
            }
        }

        @JvmStatic
        fun specify(specifier: SchemaSpecification<UserAbnormalOperationLog, SUserAbnormalOperationLog>): Specification<UserAbnormalOperationLog> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SUserAbnormalOperationLog(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SUserAbnormalOperationLog, E>,
            predicateBuilder: PredicateBuilder<SUserAbnormalOperationLog>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            return subquery(resultClass, { sq, schema ->
                sq.select(selectBuilder.build(schema))
                sq.where(predicateBuilder.build(schema))
            }, criteriaBuilder, criteriaQuery)
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            subqueryConfigure: SubqueryConfigure<E, SUserAbnormalOperationLog>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(UserAbnormalOperationLog::class.java)
            val schema = SUserAbnormalOperationLog(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): JpaPredicate<UserAbnormalOperationLog> {
            return JpaPredicate.byId(UserAbnormalOperationLog::class.java, id)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): JpaPredicate<UserAbnormalOperationLog> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(UserAbnormalOperationLog::class.java, ids as Iterable<Any>)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): JpaPredicate<UserAbnormalOperationLog> {
            return JpaPredicate.byIds(UserAbnormalOperationLog::class.java, ids.toList())
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SUserAbnormalOperationLog>): JpaPredicate<UserAbnormalOperationLog> {
            return JpaPredicate.bySpecification(UserAbnormalOperationLog::class.java, specify(builder))
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<UserAbnormalOperationLog, SUserAbnormalOperationLog>): JpaPredicate<UserAbnormalOperationLog> {
            return JpaPredicate.bySpecification(UserAbnormalOperationLog::class.java, specify(specifier))
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<UserAbnormalOperationLog> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val userId: Field<Long> by lazy {
        Field(root.get("userId"), criteriaBuilder)
    }

    val userType: Field<edu.only4.danmuku.domain.aggregates.user.enums.UserType> by lazy {
        Field(root.get("userType"), criteriaBuilder)
    }

    val opType: Field<edu.only4.danmuku.domain.aggregates.user_abnormal_operation_log.enums.AbnormalOpType> by lazy {
        Field(root.get("opType"), criteriaBuilder)
    }

    val ip: Field<String> by lazy {
        Field(root.get("ip"), criteriaBuilder)
    }

    val occurTime: Field<Long> by lazy {
        Field(root.get("occurTime"), criteriaBuilder)
    }

    val description: Field<String> by lazy {
        Field(root.get("description"), criteriaBuilder)
    }

    val extra: Field<String> by lazy {
        Field(root.get("extra"), criteriaBuilder)
    }

    val createUserId: Field<Long> by lazy {
        Field(root.get("createUserId"), criteriaBuilder)
    }

    val createBy: Field<String> by lazy {
        Field(root.get("createBy"), criteriaBuilder)
    }

    val createTime: Field<Long> by lazy {
        Field(root.get("createTime"), criteriaBuilder)
    }

    val updateUserId: Field<Long> by lazy {
        Field(root.get("updateUserId"), criteriaBuilder)
    }

    val updateBy: Field<String> by lazy {
        Field(root.get("updateBy"), criteriaBuilder)
    }

    val updateTime: Field<Long> by lazy {
        Field(root.get("updateTime"), criteriaBuilder)
    }

    val deleted: Field<Long> by lazy {
        Field(root.get("deleted"), criteriaBuilder)
    }

    fun all(vararg restrictions: Predicate): Predicate {
        return criteriaBuilder.and(*restrictions)
    }

    fun any(vararg restrictions: Predicate): Predicate {
        return criteriaBuilder.or(*restrictions)
    }

    fun allNotNull(vararg restrictions: Predicate?): Predicate? {
        val nonNullRestrictions = restrictions.filterNotNull().toTypedArray()
        return when {
            nonNullRestrictions.isEmpty() -> null
            nonNullRestrictions.size == 1 -> nonNullRestrictions[0]
            else -> criteriaBuilder.and(*nonNullRestrictions)
        }
    }

    fun anyNotNull(vararg restrictions: Predicate?): Predicate? {
        val nonNullRestrictions = restrictions.filterNotNull().toTypedArray()
        return when {
            nonNullRestrictions.isEmpty() -> null
            nonNullRestrictions.size == 1 -> nonNullRestrictions[0]
            else -> criteriaBuilder.or(*nonNullRestrictions)
        }
    }

    fun not(restriction: Predicate): Predicate = criteriaBuilder.not(restriction)

    fun spec(builder: PredicateBuilder<SUserAbnormalOperationLog>): Predicate {
        return builder.build(this)
    }
}
