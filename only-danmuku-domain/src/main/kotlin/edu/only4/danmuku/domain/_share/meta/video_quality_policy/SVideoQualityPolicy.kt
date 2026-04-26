package edu.only4.danmuku.domain._share.meta.video_quality_policy

import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePredicate
import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.video_quality_policy.AggVideoQualityPolicy
import edu.only4.danmuku.domain.aggregates.video_quality_policy.VideoQualityPolicy
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SVideoQualityPolicy(
    private val root: Path<VideoQualityPolicy>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val videoId = "videoId"

        val fileIndex = "fileIndex"

        val quality = "quality"

        val authPolicy = "authPolicy"

        val remark = "remark"

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
        fun specify(builder: PredicateBuilder<SVideoQualityPolicy>): Specification<VideoQualityPolicy> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SVideoQualityPolicy>, distinct: Boolean): Specification<VideoQualityPolicy> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoQualityPolicy>,
            vararg orderBuilders: OrderBuilder<SVideoQualityPolicy>,
        ): Specification<VideoQualityPolicy> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoQualityPolicy>,
            orderBuilders: List<OrderBuilder<SVideoQualityPolicy>>,
        ): Specification<VideoQualityPolicy> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoQualityPolicy>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideoQualityPolicy>,
        ): Specification<VideoQualityPolicy> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoQualityPolicy>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideoQualityPolicy>>,
        ): Specification<VideoQualityPolicy> {
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
        fun specify(specifier: SchemaSpecification<VideoQualityPolicy, SVideoQualityPolicy>): Specification<VideoQualityPolicy> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SVideoQualityPolicy(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SVideoQualityPolicy, E>,
            predicateBuilder: PredicateBuilder<SVideoQualityPolicy>,
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
            subqueryConfigure: SubqueryConfigure<E, SVideoQualityPolicy>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(VideoQualityPolicy::class.java)
            val schema = SVideoQualityPolicy(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): AggregatePredicate<AggVideoQualityPolicy, VideoQualityPolicy> {
            return JpaPredicate.byId(VideoQualityPolicy::class.java, id).toAggregatePredicate(AggVideoQualityPolicy::class.java)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): AggregatePredicate<AggVideoQualityPolicy, VideoQualityPolicy> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(VideoQualityPolicy::class.java, ids as Iterable<Any>).toAggregatePredicate(AggVideoQualityPolicy::class.java)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): AggregatePredicate<AggVideoQualityPolicy, VideoQualityPolicy> {
            return JpaPredicate.byIds(VideoQualityPolicy::class.java, ids.toList()).toAggregatePredicate(AggVideoQualityPolicy::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SVideoQualityPolicy>): AggregatePredicate<AggVideoQualityPolicy, VideoQualityPolicy> {
            return JpaPredicate.bySpecification(VideoQualityPolicy::class.java, specify(builder)).toAggregatePredicate(AggVideoQualityPolicy::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SVideoQualityPolicy>, distinct: Boolean): AggregatePredicate<AggVideoQualityPolicy, VideoQualityPolicy> {
            return JpaPredicate.bySpecification(VideoQualityPolicy::class.java, specify(builder, distinct)).toAggregatePredicate(AggVideoQualityPolicy::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideoQualityPolicy>,
            orderBuilders: List<OrderBuilder<SVideoQualityPolicy>>,
        ): AggregatePredicate<AggVideoQualityPolicy, VideoQualityPolicy> {
            return JpaPredicate.bySpecification(VideoQualityPolicy::class.java, specify(builder, false, orderBuilders)).toAggregatePredicate(AggVideoQualityPolicy::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideoQualityPolicy>,
            vararg orderBuilders: OrderBuilder<SVideoQualityPolicy>,
        ): AggregatePredicate<AggVideoQualityPolicy, VideoQualityPolicy> {
            return JpaPredicate.bySpecification(VideoQualityPolicy::class.java, specify(builder, false, *orderBuilders)).toAggregatePredicate(AggVideoQualityPolicy::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideoQualityPolicy>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideoQualityPolicy>>,
        ): AggregatePredicate<AggVideoQualityPolicy, VideoQualityPolicy> {
            return JpaPredicate.bySpecification(VideoQualityPolicy::class.java, specify(builder, distinct, orderBuilders)).toAggregatePredicate(AggVideoQualityPolicy::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideoQualityPolicy>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideoQualityPolicy>,
        ): AggregatePredicate<AggVideoQualityPolicy, VideoQualityPolicy> {
            return JpaPredicate.bySpecification(VideoQualityPolicy::class.java, specify(builder, distinct, *orderBuilders)).toAggregatePredicate(AggVideoQualityPolicy::class.java)
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<VideoQualityPolicy, SVideoQualityPolicy>): AggregatePredicate<AggVideoQualityPolicy, VideoQualityPolicy> {
            return JpaPredicate.bySpecification(VideoQualityPolicy::class.java, specify(specifier)).toAggregatePredicate(AggVideoQualityPolicy::class.java)
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<VideoQualityPolicy> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val videoId: Field<Long> by lazy {
        Field(root.get("videoId"), criteriaBuilder)
    }

    val fileIndex: Field<Int> by lazy {
        Field(root.get("fileIndex"), criteriaBuilder)
    }

    val quality: Field<String> by lazy {
        Field(root.get("quality"), criteriaBuilder)
    }

    val authPolicy: Field<edu.only4.danmuku.domain.aggregates.video_quality_policy.enums.QualityAuthPolicy> by lazy {
        Field(root.get("authPolicy"), criteriaBuilder)
    }

    val remark: Field<String> by lazy {
        Field(root.get("remark"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SVideoQualityPolicy>): Predicate {
        return builder.build(this)
    }
}
