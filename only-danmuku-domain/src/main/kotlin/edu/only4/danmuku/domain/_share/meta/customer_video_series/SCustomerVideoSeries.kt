package edu.only4.danmuku.domain._share.meta.customer_video_series

import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePredicate
import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.customer_video_series.AggCustomerVideoSeries
import edu.only4.danmuku.domain.aggregates.customer_video_series.CustomerVideoSeries
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SCustomerVideoSeries(
    private val root: Path<CustomerVideoSeries>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val customerId = "customerId"

        val seriesName = "seriesName"

        val seriesDescription = "seriesDescription"

        val sort = "sort"

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
        fun specify(builder: PredicateBuilder<SCustomerVideoSeries>): Specification<CustomerVideoSeries> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SCustomerVideoSeries>, distinct: Boolean): Specification<CustomerVideoSeries> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SCustomerVideoSeries>,
            vararg orderBuilders: OrderBuilder<SCustomerVideoSeries>,
        ): Specification<CustomerVideoSeries> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SCustomerVideoSeries>,
            orderBuilders: List<OrderBuilder<SCustomerVideoSeries>>,
        ): Specification<CustomerVideoSeries> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SCustomerVideoSeries>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SCustomerVideoSeries>,
        ): Specification<CustomerVideoSeries> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SCustomerVideoSeries>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SCustomerVideoSeries>>,
        ): Specification<CustomerVideoSeries> {
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
        fun specify(specifier: SchemaSpecification<CustomerVideoSeries, SCustomerVideoSeries>): Specification<CustomerVideoSeries> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SCustomerVideoSeries(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SCustomerVideoSeries, E>,
            predicateBuilder: PredicateBuilder<SCustomerVideoSeries>,
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
            subqueryConfigure: SubqueryConfigure<E, SCustomerVideoSeries>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(CustomerVideoSeries::class.java)
            val schema = SCustomerVideoSeries(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): AggregatePredicate<AggCustomerVideoSeries, CustomerVideoSeries> {
            return JpaPredicate.byId(CustomerVideoSeries::class.java, id).toAggregatePredicate(AggCustomerVideoSeries::class.java)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): AggregatePredicate<AggCustomerVideoSeries, CustomerVideoSeries> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(CustomerVideoSeries::class.java, ids as Iterable<Any>).toAggregatePredicate(AggCustomerVideoSeries::class.java)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): AggregatePredicate<AggCustomerVideoSeries, CustomerVideoSeries> {
            return JpaPredicate.byIds(CustomerVideoSeries::class.java, ids.toList()).toAggregatePredicate(AggCustomerVideoSeries::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SCustomerVideoSeries>): AggregatePredicate<AggCustomerVideoSeries, CustomerVideoSeries> {
            return JpaPredicate.bySpecification(CustomerVideoSeries::class.java, specify(builder)).toAggregatePredicate(AggCustomerVideoSeries::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SCustomerVideoSeries>, distinct: Boolean): AggregatePredicate<AggCustomerVideoSeries, CustomerVideoSeries> {
            return JpaPredicate.bySpecification(CustomerVideoSeries::class.java, specify(builder, distinct)).toAggregatePredicate(AggCustomerVideoSeries::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SCustomerVideoSeries>,
            orderBuilders: List<OrderBuilder<SCustomerVideoSeries>>,
        ): AggregatePredicate<AggCustomerVideoSeries, CustomerVideoSeries> {
            return JpaPredicate.bySpecification(CustomerVideoSeries::class.java, specify(builder, false, orderBuilders)).toAggregatePredicate(AggCustomerVideoSeries::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SCustomerVideoSeries>,
            vararg orderBuilders: OrderBuilder<SCustomerVideoSeries>,
        ): AggregatePredicate<AggCustomerVideoSeries, CustomerVideoSeries> {
            return JpaPredicate.bySpecification(CustomerVideoSeries::class.java, specify(builder, false, *orderBuilders)).toAggregatePredicate(AggCustomerVideoSeries::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SCustomerVideoSeries>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SCustomerVideoSeries>>,
        ): AggregatePredicate<AggCustomerVideoSeries, CustomerVideoSeries> {
            return JpaPredicate.bySpecification(CustomerVideoSeries::class.java, specify(builder, distinct, orderBuilders)).toAggregatePredicate(AggCustomerVideoSeries::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SCustomerVideoSeries>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SCustomerVideoSeries>,
        ): AggregatePredicate<AggCustomerVideoSeries, CustomerVideoSeries> {
            return JpaPredicate.bySpecification(CustomerVideoSeries::class.java, specify(builder, distinct, *orderBuilders)).toAggregatePredicate(AggCustomerVideoSeries::class.java)
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<CustomerVideoSeries, SCustomerVideoSeries>): AggregatePredicate<AggCustomerVideoSeries, CustomerVideoSeries> {
            return JpaPredicate.bySpecification(CustomerVideoSeries::class.java, specify(specifier)).toAggregatePredicate(AggCustomerVideoSeries::class.java)
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<CustomerVideoSeries> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val customerId: Field<Long> by lazy {
        Field(root.get("customerId"), criteriaBuilder)
    }

    val seriesName: Field<String> by lazy {
        Field(root.get("seriesName"), criteriaBuilder)
    }

    val seriesDescription: Field<String> by lazy {
        Field(root.get("seriesDescription"), criteriaBuilder)
    }

    val sort: Field<Int> by lazy {
        Field(root.get("sort"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SCustomerVideoSeries>): Predicate {
        return builder.build(this)
    }
}
