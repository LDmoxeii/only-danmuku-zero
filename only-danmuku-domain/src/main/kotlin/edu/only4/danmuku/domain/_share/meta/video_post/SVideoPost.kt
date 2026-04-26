package edu.only4.danmuku.domain._share.meta.video_post

import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.video_post.VideoPost
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SVideoPost(
    private val root: Path<VideoPost>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val videoCover = "videoCover"

        val videoName = "videoName"

        val customerId = "customerId"

        val pCategoryId = "pCategoryId"

        val categoryId = "categoryId"

        val status = "status"

        val postType = "postType"

        val originInfo = "originInfo"

        val tags = "tags"

        val introduction = "introduction"

        val interaction = "interaction"

        val duration = "duration"

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
        fun specify(builder: PredicateBuilder<SVideoPost>): Specification<VideoPost> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SVideoPost>, distinct: Boolean): Specification<VideoPost> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoPost>,
            vararg orderBuilders: OrderBuilder<SVideoPost>,
        ): Specification<VideoPost> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoPost>,
            orderBuilders: List<OrderBuilder<SVideoPost>>,
        ): Specification<VideoPost> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoPost>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideoPost>,
        ): Specification<VideoPost> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoPost>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideoPost>>,
        ): Specification<VideoPost> {
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
        fun specify(specifier: SchemaSpecification<VideoPost, SVideoPost>): Specification<VideoPost> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SVideoPost(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SVideoPost, E>,
            predicateBuilder: PredicateBuilder<SVideoPost>,
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
            subqueryConfigure: SubqueryConfigure<E, SVideoPost>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(VideoPost::class.java)
            val schema = SVideoPost(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): JpaPredicate<VideoPost> {
            return JpaPredicate.byId(VideoPost::class.java, id)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): JpaPredicate<VideoPost> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(VideoPost::class.java, ids as Iterable<Any>)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): JpaPredicate<VideoPost> {
            return JpaPredicate.byIds(VideoPost::class.java, ids.toList())
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SVideoPost>): JpaPredicate<VideoPost> {
            return JpaPredicate.bySpecification(VideoPost::class.java, specify(builder))
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<VideoPost, SVideoPost>): JpaPredicate<VideoPost> {
            return JpaPredicate.bySpecification(VideoPost::class.java, specify(specifier))
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<VideoPost> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val videoCover: Field<String> by lazy {
        Field(root.get("videoCover"), criteriaBuilder)
    }

    val videoName: Field<String> by lazy {
        Field(root.get("videoName"), criteriaBuilder)
    }

    val customerId: Field<Long> by lazy {
        Field(root.get("customerId"), criteriaBuilder)
    }

    val pCategoryId: Field<Long> by lazy {
        Field(root.get("pCategoryId"), criteriaBuilder)
    }

    val categoryId: Field<Long> by lazy {
        Field(root.get("categoryId"), criteriaBuilder)
    }

    val status: Field<edu.only4.danmuku.domain.aggregates.video_post.enums.VideoStatus> by lazy {
        Field(root.get("status"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SVideoPost>): Predicate {
        return builder.build(this)
    }
}
