package edu.only4.danmuku.domain._share.meta.statistics

import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePredicate
import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.statistics.AggStatistics
import edu.only4.danmuku.domain.aggregates.statistics.Statistics
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SStatistics(
    private val root: Path<Statistics>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val customerId = "customerId"

        val dataType = "dataType"

        val statisticsCount = "statisticsCount"

        val statisticsDate = "statisticsDate"

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
        fun specify(builder: PredicateBuilder<SStatistics>): Specification<Statistics> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SStatistics>, distinct: Boolean): Specification<Statistics> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SStatistics>,
            vararg orderBuilders: OrderBuilder<SStatistics>,
        ): Specification<Statistics> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SStatistics>,
            orderBuilders: List<OrderBuilder<SStatistics>>,
        ): Specification<Statistics> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SStatistics>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SStatistics>,
        ): Specification<Statistics> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SStatistics>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SStatistics>>,
        ): Specification<Statistics> {
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
        fun specify(specifier: SchemaSpecification<Statistics, SStatistics>): Specification<Statistics> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SStatistics(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SStatistics, E>,
            predicateBuilder: PredicateBuilder<SStatistics>,
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
            subqueryConfigure: SubqueryConfigure<E, SStatistics>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(Statistics::class.java)
            val schema = SStatistics(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): AggregatePredicate<AggStatistics, Statistics> {
            return JpaPredicate.byId(Statistics::class.java, id).toAggregatePredicate(AggStatistics::class.java)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): AggregatePredicate<AggStatistics, Statistics> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(Statistics::class.java, ids as Iterable<Any>).toAggregatePredicate(AggStatistics::class.java)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): AggregatePredicate<AggStatistics, Statistics> {
            return JpaPredicate.byIds(Statistics::class.java, ids.toList()).toAggregatePredicate(AggStatistics::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SStatistics>): AggregatePredicate<AggStatistics, Statistics> {
            return JpaPredicate.bySpecification(Statistics::class.java, specify(builder)).toAggregatePredicate(AggStatistics::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SStatistics>, distinct: Boolean): AggregatePredicate<AggStatistics, Statistics> {
            return JpaPredicate.bySpecification(Statistics::class.java, specify(builder, distinct)).toAggregatePredicate(AggStatistics::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SStatistics>,
            orderBuilders: List<OrderBuilder<SStatistics>>,
        ): AggregatePredicate<AggStatistics, Statistics> {
            return JpaPredicate.bySpecification(Statistics::class.java, specify(builder, false, orderBuilders)).toAggregatePredicate(AggStatistics::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SStatistics>,
            vararg orderBuilders: OrderBuilder<SStatistics>,
        ): AggregatePredicate<AggStatistics, Statistics> {
            return JpaPredicate.bySpecification(Statistics::class.java, specify(builder, false, *orderBuilders)).toAggregatePredicate(AggStatistics::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SStatistics>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SStatistics>>,
        ): AggregatePredicate<AggStatistics, Statistics> {
            return JpaPredicate.bySpecification(Statistics::class.java, specify(builder, distinct, orderBuilders)).toAggregatePredicate(AggStatistics::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SStatistics>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SStatistics>,
        ): AggregatePredicate<AggStatistics, Statistics> {
            return JpaPredicate.bySpecification(Statistics::class.java, specify(builder, distinct, *orderBuilders)).toAggregatePredicate(AggStatistics::class.java)
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<Statistics, SStatistics>): AggregatePredicate<AggStatistics, Statistics> {
            return JpaPredicate.bySpecification(Statistics::class.java, specify(specifier)).toAggregatePredicate(AggStatistics::class.java)
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<Statistics> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val customerId: Field<Long> by lazy {
        Field(root.get("customerId"), criteriaBuilder)
    }

    val dataType: Field<edu.only4.danmuku.domain.aggregates.statistics.enums.StatisticsDataType> by lazy {
        Field(root.get("dataType"), criteriaBuilder)
    }

    val statisticsCount: Field<Int> by lazy {
        Field(root.get("statisticsCount"), criteriaBuilder)
    }

    val statisticsDate: Field<Long> by lazy {
        Field(root.get("statisticsDate"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SStatistics>): Predicate {
        return builder.build(this)
    }
}
