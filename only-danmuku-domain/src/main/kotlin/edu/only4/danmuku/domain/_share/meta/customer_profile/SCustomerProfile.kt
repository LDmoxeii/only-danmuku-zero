package edu.only4.danmuku.domain._share.meta.customer_profile

import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePredicate
import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.customer_profile.AggCustomerProfile
import edu.only4.danmuku.domain.aggregates.customer_profile.CustomerProfile
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SCustomerProfile(
    private val root: Path<CustomerProfile>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val userId = "userId"

        val nickName = "nickName"

        val avatar = "avatar"

        val email = "email"

        val phone = "phone"

        val sex = "sex"

        val birthday = "birthday"

        val school = "school"

        val personIntroduction = "personIntroduction"

        val noticeInfo = "noticeInfo"

        val totalCoinCount = "totalCoinCount"

        val currentCoinCount = "currentCoinCount"

        val theme = "theme"

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
        fun specify(builder: PredicateBuilder<SCustomerProfile>): Specification<CustomerProfile> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SCustomerProfile>, distinct: Boolean): Specification<CustomerProfile> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SCustomerProfile>,
            vararg orderBuilders: OrderBuilder<SCustomerProfile>,
        ): Specification<CustomerProfile> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SCustomerProfile>,
            orderBuilders: List<OrderBuilder<SCustomerProfile>>,
        ): Specification<CustomerProfile> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SCustomerProfile>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SCustomerProfile>,
        ): Specification<CustomerProfile> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SCustomerProfile>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SCustomerProfile>>,
        ): Specification<CustomerProfile> {
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
        fun specify(specifier: SchemaSpecification<CustomerProfile, SCustomerProfile>): Specification<CustomerProfile> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SCustomerProfile(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SCustomerProfile, E>,
            predicateBuilder: PredicateBuilder<SCustomerProfile>,
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
            subqueryConfigure: SubqueryConfigure<E, SCustomerProfile>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(CustomerProfile::class.java)
            val schema = SCustomerProfile(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): AggregatePredicate<AggCustomerProfile, CustomerProfile> {
            return JpaPredicate.byId(CustomerProfile::class.java, id).toAggregatePredicate(AggCustomerProfile::class.java)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): AggregatePredicate<AggCustomerProfile, CustomerProfile> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(CustomerProfile::class.java, ids as Iterable<Any>).toAggregatePredicate(AggCustomerProfile::class.java)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): AggregatePredicate<AggCustomerProfile, CustomerProfile> {
            return JpaPredicate.byIds(CustomerProfile::class.java, ids.toList()).toAggregatePredicate(AggCustomerProfile::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SCustomerProfile>): AggregatePredicate<AggCustomerProfile, CustomerProfile> {
            return JpaPredicate.bySpecification(CustomerProfile::class.java, specify(builder)).toAggregatePredicate(AggCustomerProfile::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SCustomerProfile>, distinct: Boolean): AggregatePredicate<AggCustomerProfile, CustomerProfile> {
            return JpaPredicate.bySpecification(CustomerProfile::class.java, specify(builder, distinct)).toAggregatePredicate(AggCustomerProfile::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SCustomerProfile>,
            orderBuilders: List<OrderBuilder<SCustomerProfile>>,
        ): AggregatePredicate<AggCustomerProfile, CustomerProfile> {
            return JpaPredicate.bySpecification(CustomerProfile::class.java, specify(builder, false, orderBuilders)).toAggregatePredicate(AggCustomerProfile::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SCustomerProfile>,
            vararg orderBuilders: OrderBuilder<SCustomerProfile>,
        ): AggregatePredicate<AggCustomerProfile, CustomerProfile> {
            return JpaPredicate.bySpecification(CustomerProfile::class.java, specify(builder, false, *orderBuilders)).toAggregatePredicate(AggCustomerProfile::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SCustomerProfile>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SCustomerProfile>>,
        ): AggregatePredicate<AggCustomerProfile, CustomerProfile> {
            return JpaPredicate.bySpecification(CustomerProfile::class.java, specify(builder, distinct, orderBuilders)).toAggregatePredicate(AggCustomerProfile::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SCustomerProfile>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SCustomerProfile>,
        ): AggregatePredicate<AggCustomerProfile, CustomerProfile> {
            return JpaPredicate.bySpecification(CustomerProfile::class.java, specify(builder, distinct, *orderBuilders)).toAggregatePredicate(AggCustomerProfile::class.java)
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<CustomerProfile, SCustomerProfile>): AggregatePredicate<AggCustomerProfile, CustomerProfile> {
            return JpaPredicate.bySpecification(CustomerProfile::class.java, specify(specifier)).toAggregatePredicate(AggCustomerProfile::class.java)
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<CustomerProfile> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val userId: Field<Long> by lazy {
        Field(root.get("userId"), criteriaBuilder)
    }

    val nickName: Field<String> by lazy {
        Field(root.get("nickName"), criteriaBuilder)
    }

    val avatar: Field<String> by lazy {
        Field(root.get("avatar"), criteriaBuilder)
    }

    val email: Field<String> by lazy {
        Field(root.get("email"), criteriaBuilder)
    }

    val phone: Field<String> by lazy {
        Field(root.get("phone"), criteriaBuilder)
    }

    val sex: Field<edu.only4.danmuku.domain.aggregates.customer_profile.enums.SexType> by lazy {
        Field(root.get("sex"), criteriaBuilder)
    }

    val birthday: Field<String> by lazy {
        Field(root.get("birthday"), criteriaBuilder)
    }

    val school: Field<String> by lazy {
        Field(root.get("school"), criteriaBuilder)
    }

    val personIntroduction: Field<String> by lazy {
        Field(root.get("personIntroduction"), criteriaBuilder)
    }

    val noticeInfo: Field<String> by lazy {
        Field(root.get("noticeInfo"), criteriaBuilder)
    }

    val totalCoinCount: Field<Int> by lazy {
        Field(root.get("totalCoinCount"), criteriaBuilder)
    }

    val currentCoinCount: Field<Int> by lazy {
        Field(root.get("currentCoinCount"), criteriaBuilder)
    }

    val theme: Field<edu.only4.danmuku.domain.aggregates.customer_profile.enums.ThemeType> by lazy {
        Field(root.get("theme"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SCustomerProfile>): Predicate {
        return builder.build(this)
    }
}
