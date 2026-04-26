package edu.only4.danmuku.domain._share.meta.video_audit_trace

import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePredicate
import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.video_audit_trace.AggVideoAuditTrace
import edu.only4.danmuku.domain.aggregates.video_audit_trace.VideoAuditTrace
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SVideoAuditTrace(
    private val root: Path<VideoAuditTrace>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val videoPostId = "videoPostId"

        val auditStatus = "auditStatus"

        val reviewerId = "reviewerId"

        val reviewerType = "reviewerType"

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
        fun specify(builder: PredicateBuilder<SVideoAuditTrace>): Specification<VideoAuditTrace> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SVideoAuditTrace>, distinct: Boolean): Specification<VideoAuditTrace> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoAuditTrace>,
            vararg orderBuilders: OrderBuilder<SVideoAuditTrace>,
        ): Specification<VideoAuditTrace> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoAuditTrace>,
            orderBuilders: List<OrderBuilder<SVideoAuditTrace>>,
        ): Specification<VideoAuditTrace> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoAuditTrace>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideoAuditTrace>,
        ): Specification<VideoAuditTrace> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoAuditTrace>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideoAuditTrace>>,
        ): Specification<VideoAuditTrace> {
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
        fun specify(specifier: SchemaSpecification<VideoAuditTrace, SVideoAuditTrace>): Specification<VideoAuditTrace> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SVideoAuditTrace(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SVideoAuditTrace, E>,
            predicateBuilder: PredicateBuilder<SVideoAuditTrace>,
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
            subqueryConfigure: SubqueryConfigure<E, SVideoAuditTrace>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(VideoAuditTrace::class.java)
            val schema = SVideoAuditTrace(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): AggregatePredicate<AggVideoAuditTrace, VideoAuditTrace> {
            return JpaPredicate.byId(VideoAuditTrace::class.java, id).toAggregatePredicate(AggVideoAuditTrace::class.java)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): AggregatePredicate<AggVideoAuditTrace, VideoAuditTrace> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(VideoAuditTrace::class.java, ids as Iterable<Any>).toAggregatePredicate(AggVideoAuditTrace::class.java)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): AggregatePredicate<AggVideoAuditTrace, VideoAuditTrace> {
            return JpaPredicate.byIds(VideoAuditTrace::class.java, ids.toList()).toAggregatePredicate(AggVideoAuditTrace::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SVideoAuditTrace>): AggregatePredicate<AggVideoAuditTrace, VideoAuditTrace> {
            return JpaPredicate.bySpecification(VideoAuditTrace::class.java, specify(builder)).toAggregatePredicate(AggVideoAuditTrace::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SVideoAuditTrace>, distinct: Boolean): AggregatePredicate<AggVideoAuditTrace, VideoAuditTrace> {
            return JpaPredicate.bySpecification(VideoAuditTrace::class.java, specify(builder, distinct)).toAggregatePredicate(AggVideoAuditTrace::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideoAuditTrace>,
            orderBuilders: List<OrderBuilder<SVideoAuditTrace>>,
        ): AggregatePredicate<AggVideoAuditTrace, VideoAuditTrace> {
            return JpaPredicate.bySpecification(VideoAuditTrace::class.java, specify(builder, false, orderBuilders)).toAggregatePredicate(AggVideoAuditTrace::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideoAuditTrace>,
            vararg orderBuilders: OrderBuilder<SVideoAuditTrace>,
        ): AggregatePredicate<AggVideoAuditTrace, VideoAuditTrace> {
            return JpaPredicate.bySpecification(VideoAuditTrace::class.java, specify(builder, false, *orderBuilders)).toAggregatePredicate(AggVideoAuditTrace::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideoAuditTrace>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideoAuditTrace>>,
        ): AggregatePredicate<AggVideoAuditTrace, VideoAuditTrace> {
            return JpaPredicate.bySpecification(VideoAuditTrace::class.java, specify(builder, distinct, orderBuilders)).toAggregatePredicate(AggVideoAuditTrace::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideoAuditTrace>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideoAuditTrace>,
        ): AggregatePredicate<AggVideoAuditTrace, VideoAuditTrace> {
            return JpaPredicate.bySpecification(VideoAuditTrace::class.java, specify(builder, distinct, *orderBuilders)).toAggregatePredicate(AggVideoAuditTrace::class.java)
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<VideoAuditTrace, SVideoAuditTrace>): AggregatePredicate<AggVideoAuditTrace, VideoAuditTrace> {
            return JpaPredicate.bySpecification(VideoAuditTrace::class.java, specify(specifier)).toAggregatePredicate(AggVideoAuditTrace::class.java)
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<VideoAuditTrace> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val videoPostId: Field<Long> by lazy {
        Field(root.get("videoPostId"), criteriaBuilder)
    }

    val auditStatus: Field<edu.only4.danmuku.domain.aggregates.video_audit_trace.enums.AuditStatus> by lazy {
        Field(root.get("auditStatus"), criteriaBuilder)
    }

    val reviewerId: Field<Long> by lazy {
        Field(root.get("reviewerId"), criteriaBuilder)
    }

    val reviewerType: Field<edu.only4.danmuku.domain.aggregates.user.enums.UserType> by lazy {
        Field(root.get("reviewerType"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SVideoAuditTrace>): Predicate {
        return builder.build(this)
    }
}
