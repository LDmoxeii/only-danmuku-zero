package edu.only4.danmuku.domain._share.meta.user_login_log

import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePredicate
import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.user_login_log.AggUserLoginLog
import edu.only4.danmuku.domain.aggregates.user_login_log.UserLoginLog
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SUserLoginLog(
    private val root: Path<UserLoginLog>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val userId = "userId"

        val userType = "userType"

        val loginName = "loginName"

        val loginType = "loginType"

        val result = "result"

        val ip = "ip"

        val userAgent = "userAgent"

        val reason = "reason"

        val occurTime = "occurTime"

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
        fun specify(builder: PredicateBuilder<SUserLoginLog>): Specification<UserLoginLog> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SUserLoginLog>, distinct: Boolean): Specification<UserLoginLog> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SUserLoginLog>,
            vararg orderBuilders: OrderBuilder<SUserLoginLog>,
        ): Specification<UserLoginLog> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SUserLoginLog>,
            orderBuilders: List<OrderBuilder<SUserLoginLog>>,
        ): Specification<UserLoginLog> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SUserLoginLog>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SUserLoginLog>,
        ): Specification<UserLoginLog> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SUserLoginLog>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SUserLoginLog>>,
        ): Specification<UserLoginLog> {
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
        fun specify(specifier: SchemaSpecification<UserLoginLog, SUserLoginLog>): Specification<UserLoginLog> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SUserLoginLog(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SUserLoginLog, E>,
            predicateBuilder: PredicateBuilder<SUserLoginLog>,
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
            subqueryConfigure: SubqueryConfigure<E, SUserLoginLog>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(UserLoginLog::class.java)
            val schema = SUserLoginLog(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): AggregatePredicate<AggUserLoginLog, UserLoginLog> {
            return JpaPredicate.byId(UserLoginLog::class.java, id).toAggregatePredicate(AggUserLoginLog::class.java)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): AggregatePredicate<AggUserLoginLog, UserLoginLog> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(UserLoginLog::class.java, ids as Iterable<Any>).toAggregatePredicate(AggUserLoginLog::class.java)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): AggregatePredicate<AggUserLoginLog, UserLoginLog> {
            return JpaPredicate.byIds(UserLoginLog::class.java, ids.toList()).toAggregatePredicate(AggUserLoginLog::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SUserLoginLog>): AggregatePredicate<AggUserLoginLog, UserLoginLog> {
            return JpaPredicate.bySpecification(UserLoginLog::class.java, specify(builder)).toAggregatePredicate(AggUserLoginLog::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SUserLoginLog>, distinct: Boolean): AggregatePredicate<AggUserLoginLog, UserLoginLog> {
            return JpaPredicate.bySpecification(UserLoginLog::class.java, specify(builder, distinct)).toAggregatePredicate(AggUserLoginLog::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SUserLoginLog>,
            orderBuilders: List<OrderBuilder<SUserLoginLog>>,
        ): AggregatePredicate<AggUserLoginLog, UserLoginLog> {
            return JpaPredicate.bySpecification(UserLoginLog::class.java, specify(builder, false, orderBuilders)).toAggregatePredicate(AggUserLoginLog::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SUserLoginLog>,
            vararg orderBuilders: OrderBuilder<SUserLoginLog>,
        ): AggregatePredicate<AggUserLoginLog, UserLoginLog> {
            return JpaPredicate.bySpecification(UserLoginLog::class.java, specify(builder, false, *orderBuilders)).toAggregatePredicate(AggUserLoginLog::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SUserLoginLog>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SUserLoginLog>>,
        ): AggregatePredicate<AggUserLoginLog, UserLoginLog> {
            return JpaPredicate.bySpecification(UserLoginLog::class.java, specify(builder, distinct, orderBuilders)).toAggregatePredicate(AggUserLoginLog::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SUserLoginLog>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SUserLoginLog>,
        ): AggregatePredicate<AggUserLoginLog, UserLoginLog> {
            return JpaPredicate.bySpecification(UserLoginLog::class.java, specify(builder, distinct, *orderBuilders)).toAggregatePredicate(AggUserLoginLog::class.java)
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<UserLoginLog, SUserLoginLog>): AggregatePredicate<AggUserLoginLog, UserLoginLog> {
            return JpaPredicate.bySpecification(UserLoginLog::class.java, specify(specifier)).toAggregatePredicate(AggUserLoginLog::class.java)
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<UserLoginLog> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val userId: Field<Long> by lazy {
        Field(root.get("userId"), criteriaBuilder)
    }

    val userType: Field<edu.only4.danmuku.domain.aggregates.user.enums.UserType> by lazy {
        Field(root.get("userType"), criteriaBuilder)
    }

    val loginName: Field<String> by lazy {
        Field(root.get("loginName"), criteriaBuilder)
    }

    val loginType: Field<edu.only4.danmuku.domain.aggregates.user_login_log.enums.LoginType> by lazy {
        Field(root.get("loginType"), criteriaBuilder)
    }

    val result: Field<edu.only4.danmuku.domain.aggregates.user_login_log.enums.LoginResult> by lazy {
        Field(root.get("result"), criteriaBuilder)
    }

    val ip: Field<String> by lazy {
        Field(root.get("ip"), criteriaBuilder)
    }

    val userAgent: Field<String> by lazy {
        Field(root.get("userAgent"), criteriaBuilder)
    }

    val reason: Field<String> by lazy {
        Field(root.get("reason"), criteriaBuilder)
    }

    val occurTime: Field<Long> by lazy {
        Field(root.get("occurTime"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SUserLoginLog>): Predicate {
        return builder.build(this)
    }
}
