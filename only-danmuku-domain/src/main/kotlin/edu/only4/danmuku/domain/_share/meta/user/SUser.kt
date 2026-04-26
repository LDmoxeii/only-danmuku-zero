package edu.only4.danmuku.domain._share.meta.user

import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.user.User
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SUser(
    private val root: Path<User>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val type = "type"

        val nickName = "nickName"

        val email = "email"

        val phone = "phone"

        val password = "password"

        val joinTime = "joinTime"

        val lastLoginTime = "lastLoginTime"

        val lastLoginIp = "lastLoginIp"

        val status = "status"

        val relatedId = "relatedId"

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
        fun specify(builder: PredicateBuilder<SUser>): Specification<User> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SUser>, distinct: Boolean): Specification<User> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SUser>,
            vararg orderBuilders: OrderBuilder<SUser>,
        ): Specification<User> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SUser>,
            orderBuilders: List<OrderBuilder<SUser>>,
        ): Specification<User> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SUser>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SUser>,
        ): Specification<User> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SUser>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SUser>>,
        ): Specification<User> {
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
        fun specify(specifier: SchemaSpecification<User, SUser>): Specification<User> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SUser(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SUser, E>,
            predicateBuilder: PredicateBuilder<SUser>,
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
            subqueryConfigure: SubqueryConfigure<E, SUser>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(User::class.java)
            val schema = SUser(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): JpaPredicate<User> {
            return JpaPredicate.byId(User::class.java, id)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): JpaPredicate<User> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(User::class.java, ids as Iterable<Any>)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): JpaPredicate<User> {
            return JpaPredicate.byIds(User::class.java, ids.toList())
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SUser>): JpaPredicate<User> {
            return JpaPredicate.bySpecification(User::class.java, specify(builder))
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<User, SUser>): JpaPredicate<User> {
            return JpaPredicate.bySpecification(User::class.java, specify(specifier))
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<User> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val type: Field<edu.only4.danmuku.domain.aggregates.user.enums.UserType> by lazy {
        Field(root.get("type"), criteriaBuilder)
    }

    val nickName: Field<String> by lazy {
        Field(root.get("nickName"), criteriaBuilder)
    }

    val email: Field<String> by lazy {
        Field(root.get("email"), criteriaBuilder)
    }

    val phone: Field<String> by lazy {
        Field(root.get("phone"), criteriaBuilder)
    }

    val password: Field<String> by lazy {
        Field(root.get("password"), criteriaBuilder)
    }

    val joinTime: Field<Long> by lazy {
        Field(root.get("joinTime"), criteriaBuilder)
    }

    val lastLoginTime: Field<Long> by lazy {
        Field(root.get("lastLoginTime"), criteriaBuilder)
    }

    val lastLoginIp: Field<String> by lazy {
        Field(root.get("lastLoginIp"), criteriaBuilder)
    }

    val status: Field<Int> by lazy {
        Field(root.get("status"), criteriaBuilder)
    }

    val relatedId: Field<Long> by lazy {
        Field(root.get("relatedId"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SUser>): Predicate {
        return builder.build(this)
    }
}
