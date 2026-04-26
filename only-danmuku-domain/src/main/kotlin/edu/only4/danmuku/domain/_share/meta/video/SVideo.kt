package edu.only4.danmuku.domain._share.meta.video

import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePredicate
import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.video.AggVideo
import edu.only4.danmuku.domain.aggregates.video.Video
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SVideo(
    private val root: Path<Video>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val videoPostId = "videoPostId"

        val customerId = "customerId"

        val videoCover = "videoCover"

        val videoName = "videoName"

        val pCategoryId = "pCategoryId"

        val categoryId = "categoryId"

        val postType = "postType"

        val originInfo = "originInfo"

        val tags = "tags"

        val introduction = "introduction"

        val interaction = "interaction"

        val duration = "duration"

        val playCount = "playCount"

        val likeCount = "likeCount"

        val danmukuCount = "danmukuCount"

        val commentCount = "commentCount"

        val coinCount = "coinCount"

        val collectCount = "collectCount"

        val recommendType = "recommendType"

        val lastPlayTime = "lastPlayTime"

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
        fun specify(builder: PredicateBuilder<SVideo>): Specification<Video> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SVideo>, distinct: Boolean): Specification<Video> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideo>,
            vararg orderBuilders: OrderBuilder<SVideo>,
        ): Specification<Video> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideo>,
            orderBuilders: List<OrderBuilder<SVideo>>,
        ): Specification<Video> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideo>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideo>,
        ): Specification<Video> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideo>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideo>>,
        ): Specification<Video> {
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
        fun specify(specifier: SchemaSpecification<Video, SVideo>): Specification<Video> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SVideo(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SVideo, E>,
            predicateBuilder: PredicateBuilder<SVideo>,
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
            subqueryConfigure: SubqueryConfigure<E, SVideo>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(Video::class.java)
            val schema = SVideo(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): AggregatePredicate<AggVideo, Video> {
            return JpaPredicate.byId(Video::class.java, id).toAggregatePredicate(AggVideo::class.java)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): AggregatePredicate<AggVideo, Video> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(Video::class.java, ids as Iterable<Any>).toAggregatePredicate(AggVideo::class.java)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): AggregatePredicate<AggVideo, Video> {
            return JpaPredicate.byIds(Video::class.java, ids.toList()).toAggregatePredicate(AggVideo::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SVideo>): AggregatePredicate<AggVideo, Video> {
            return JpaPredicate.bySpecification(Video::class.java, specify(builder)).toAggregatePredicate(AggVideo::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SVideo>, distinct: Boolean): AggregatePredicate<AggVideo, Video> {
            return JpaPredicate.bySpecification(Video::class.java, specify(builder, distinct)).toAggregatePredicate(AggVideo::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideo>,
            orderBuilders: List<OrderBuilder<SVideo>>,
        ): AggregatePredicate<AggVideo, Video> {
            return JpaPredicate.bySpecification(Video::class.java, specify(builder, false, orderBuilders)).toAggregatePredicate(AggVideo::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideo>,
            vararg orderBuilders: OrderBuilder<SVideo>,
        ): AggregatePredicate<AggVideo, Video> {
            return JpaPredicate.bySpecification(Video::class.java, specify(builder, false, *orderBuilders)).toAggregatePredicate(AggVideo::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideo>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideo>>,
        ): AggregatePredicate<AggVideo, Video> {
            return JpaPredicate.bySpecification(Video::class.java, specify(builder, distinct, orderBuilders)).toAggregatePredicate(AggVideo::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideo>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideo>,
        ): AggregatePredicate<AggVideo, Video> {
            return JpaPredicate.bySpecification(Video::class.java, specify(builder, distinct, *orderBuilders)).toAggregatePredicate(AggVideo::class.java)
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<Video, SVideo>): AggregatePredicate<AggVideo, Video> {
            return JpaPredicate.bySpecification(Video::class.java, specify(specifier)).toAggregatePredicate(AggVideo::class.java)
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<Video> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val videoPostId: Field<Long> by lazy {
        Field(root.get("videoPostId"), criteriaBuilder)
    }

    val customerId: Field<Long> by lazy {
        Field(root.get("customerId"), criteriaBuilder)
    }

    val videoCover: Field<String> by lazy {
        Field(root.get("videoCover"), criteriaBuilder)
    }

    val videoName: Field<String> by lazy {
        Field(root.get("videoName"), criteriaBuilder)
    }

    val pCategoryId: Field<Long> by lazy {
        Field(root.get("pCategoryId"), criteriaBuilder)
    }

    val categoryId: Field<Long> by lazy {
        Field(root.get("categoryId"), criteriaBuilder)
    }

    val postType: Field<edu.only4.danmuku.domain.aggregates.video_post.enums.PostType> by lazy {
        Field(root.get("postType"), criteriaBuilder)
    }

    val originInfo: Field<String> by lazy {
        Field(root.get("originInfo"), criteriaBuilder)
    }

    val tags: Field<String> by lazy {
        Field(root.get("tags"), criteriaBuilder)
    }

    val introduction: Field<String> by lazy {
        Field(root.get("introduction"), criteriaBuilder)
    }

    val interaction: Field<String> by lazy {
        Field(root.get("interaction"), criteriaBuilder)
    }

    val duration: Field<Int> by lazy {
        Field(root.get("duration"), criteriaBuilder)
    }

    val playCount: Field<Int> by lazy {
        Field(root.get("playCount"), criteriaBuilder)
    }

    val likeCount: Field<Int> by lazy {
        Field(root.get("likeCount"), criteriaBuilder)
    }

    val danmukuCount: Field<Int> by lazy {
        Field(root.get("danmukuCount"), criteriaBuilder)
    }

    val commentCount: Field<Int> by lazy {
        Field(root.get("commentCount"), criteriaBuilder)
    }

    val coinCount: Field<Int> by lazy {
        Field(root.get("coinCount"), criteriaBuilder)
    }

    val collectCount: Field<Int> by lazy {
        Field(root.get("collectCount"), criteriaBuilder)
    }

    val recommendType: Field<edu.only4.danmuku.domain.aggregates.video.enums.RecommendType> by lazy {
        Field(root.get("recommendType"), criteriaBuilder)
    }

    val lastPlayTime: Field<Long> by lazy {
        Field(root.get("lastPlayTime"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SVideo>): Predicate {
        return builder.build(this)
    }
}
