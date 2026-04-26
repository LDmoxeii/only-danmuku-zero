package edu.only4.danmuku.domain._share.meta.video_comment

import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePredicate
import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.video_comment.AggVideoComment
import edu.only4.danmuku.domain.aggregates.video_comment.VideoComment
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SVideoComment(
    private val root: Path<VideoComment>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val parentId = "parentId"

        val videoId = "videoId"

        val videoOwnerId = "videoOwnerId"

        val content = "content"

        val imgPath = "imgPath"

        val customerId = "customerId"

        val replyCustomerId = "replyCustomerId"

        val topType = "topType"

        val postTime = "postTime"

        val likeCount = "likeCount"

        val hateCount = "hateCount"

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
        fun specify(builder: PredicateBuilder<SVideoComment>): Specification<VideoComment> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SVideoComment>, distinct: Boolean): Specification<VideoComment> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoComment>,
            vararg orderBuilders: OrderBuilder<SVideoComment>,
        ): Specification<VideoComment> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoComment>,
            orderBuilders: List<OrderBuilder<SVideoComment>>,
        ): Specification<VideoComment> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoComment>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideoComment>,
        ): Specification<VideoComment> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoComment>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideoComment>>,
        ): Specification<VideoComment> {
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
        fun specify(specifier: SchemaSpecification<VideoComment, SVideoComment>): Specification<VideoComment> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SVideoComment(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SVideoComment, E>,
            predicateBuilder: PredicateBuilder<SVideoComment>,
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
            subqueryConfigure: SubqueryConfigure<E, SVideoComment>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(VideoComment::class.java)
            val schema = SVideoComment(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): AggregatePredicate<AggVideoComment, VideoComment> {
            return JpaPredicate.byId(VideoComment::class.java, id).toAggregatePredicate(AggVideoComment::class.java)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): AggregatePredicate<AggVideoComment, VideoComment> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(VideoComment::class.java, ids as Iterable<Any>).toAggregatePredicate(AggVideoComment::class.java)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): AggregatePredicate<AggVideoComment, VideoComment> {
            return JpaPredicate.byIds(VideoComment::class.java, ids.toList()).toAggregatePredicate(AggVideoComment::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SVideoComment>): AggregatePredicate<AggVideoComment, VideoComment> {
            return JpaPredicate.bySpecification(VideoComment::class.java, specify(builder)).toAggregatePredicate(AggVideoComment::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SVideoComment>, distinct: Boolean): AggregatePredicate<AggVideoComment, VideoComment> {
            return JpaPredicate.bySpecification(VideoComment::class.java, specify(builder, distinct)).toAggregatePredicate(AggVideoComment::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideoComment>,
            orderBuilders: List<OrderBuilder<SVideoComment>>,
        ): AggregatePredicate<AggVideoComment, VideoComment> {
            return JpaPredicate.bySpecification(VideoComment::class.java, specify(builder, false, orderBuilders)).toAggregatePredicate(AggVideoComment::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideoComment>,
            vararg orderBuilders: OrderBuilder<SVideoComment>,
        ): AggregatePredicate<AggVideoComment, VideoComment> {
            return JpaPredicate.bySpecification(VideoComment::class.java, specify(builder, false, *orderBuilders)).toAggregatePredicate(AggVideoComment::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideoComment>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideoComment>>,
        ): AggregatePredicate<AggVideoComment, VideoComment> {
            return JpaPredicate.bySpecification(VideoComment::class.java, specify(builder, distinct, orderBuilders)).toAggregatePredicate(AggVideoComment::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideoComment>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideoComment>,
        ): AggregatePredicate<AggVideoComment, VideoComment> {
            return JpaPredicate.bySpecification(VideoComment::class.java, specify(builder, distinct, *orderBuilders)).toAggregatePredicate(AggVideoComment::class.java)
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<VideoComment, SVideoComment>): AggregatePredicate<AggVideoComment, VideoComment> {
            return JpaPredicate.bySpecification(VideoComment::class.java, specify(specifier)).toAggregatePredicate(AggVideoComment::class.java)
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<VideoComment> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val parentId: Field<Long> by lazy {
        Field(root.get("parentId"), criteriaBuilder)
    }

    val videoId: Field<Long> by lazy {
        Field(root.get("videoId"), criteriaBuilder)
    }

    val videoOwnerId: Field<Long> by lazy {
        Field(root.get("videoOwnerId"), criteriaBuilder)
    }

    val content: Field<String> by lazy {
        Field(root.get("content"), criteriaBuilder)
    }

    val imgPath: Field<String> by lazy {
        Field(root.get("imgPath"), criteriaBuilder)
    }

    val customerId: Field<Long> by lazy {
        Field(root.get("customerId"), criteriaBuilder)
    }

    val replyCustomerId: Field<Long> by lazy {
        Field(root.get("replyCustomerId"), criteriaBuilder)
    }

    val topType: Field<Int> by lazy {
        Field(root.get("topType"), criteriaBuilder)
    }

    val postTime: Field<Long> by lazy {
        Field(root.get("postTime"), criteriaBuilder)
    }

    val likeCount: Field<Int> by lazy {
        Field(root.get("likeCount"), criteriaBuilder)
    }

    val hateCount: Field<Int> by lazy {
        Field(root.get("hateCount"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SVideoComment>): Predicate {
        return builder.build(this)
    }
}
