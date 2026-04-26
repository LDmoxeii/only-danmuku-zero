package edu.only4.danmuku.domain._share.meta.video_post_processing

import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.video_post_processing.VideoPostProcessing
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SVideoPostProcessing(
    private val root: Path<VideoPostProcessing>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val videoPostId = "videoPostId"

        val totalFiles = "totalFiles"

        val transcodeStatus = "transcodeStatus"

        val encryptStatus = "encryptStatus"

        val transcodeDoneCount = "transcodeDoneCount"

        val encryptDoneCount = "encryptDoneCount"

        val failedCount = "failedCount"

        val lastFailReason = "lastFailReason"

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
        fun specify(builder: PredicateBuilder<SVideoPostProcessing>): Specification<VideoPostProcessing> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SVideoPostProcessing>, distinct: Boolean): Specification<VideoPostProcessing> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoPostProcessing>,
            vararg orderBuilders: OrderBuilder<SVideoPostProcessing>,
        ): Specification<VideoPostProcessing> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoPostProcessing>,
            orderBuilders: List<OrderBuilder<SVideoPostProcessing>>,
        ): Specification<VideoPostProcessing> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoPostProcessing>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideoPostProcessing>,
        ): Specification<VideoPostProcessing> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoPostProcessing>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideoPostProcessing>>,
        ): Specification<VideoPostProcessing> {
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
        fun specify(specifier: SchemaSpecification<VideoPostProcessing, SVideoPostProcessing>): Specification<VideoPostProcessing> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SVideoPostProcessing(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SVideoPostProcessing, E>,
            predicateBuilder: PredicateBuilder<SVideoPostProcessing>,
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
            subqueryConfigure: SubqueryConfigure<E, SVideoPostProcessing>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(VideoPostProcessing::class.java)
            val schema = SVideoPostProcessing(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): JpaPredicate<VideoPostProcessing> {
            return JpaPredicate.byId(VideoPostProcessing::class.java, id)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): JpaPredicate<VideoPostProcessing> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(VideoPostProcessing::class.java, ids as Iterable<Any>)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): JpaPredicate<VideoPostProcessing> {
            return JpaPredicate.byIds(VideoPostProcessing::class.java, ids.toList())
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SVideoPostProcessing>): JpaPredicate<VideoPostProcessing> {
            return JpaPredicate.bySpecification(VideoPostProcessing::class.java, specify(builder))
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<VideoPostProcessing, SVideoPostProcessing>): JpaPredicate<VideoPostProcessing> {
            return JpaPredicate.bySpecification(VideoPostProcessing::class.java, specify(specifier))
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<VideoPostProcessing> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val videoPostId: Field<Long> by lazy {
        Field(root.get("videoPostId"), criteriaBuilder)
    }

    val totalFiles: Field<Int> by lazy {
        Field(root.get("totalFiles"), criteriaBuilder)
    }

    val transcodeStatus: Field<edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus> by lazy {
        Field(root.get("transcodeStatus"), criteriaBuilder)
    }

    val encryptStatus: Field<edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus> by lazy {
        Field(root.get("encryptStatus"), criteriaBuilder)
    }

    val transcodeDoneCount: Field<Int> by lazy {
        Field(root.get("transcodeDoneCount"), criteriaBuilder)
    }

    val encryptDoneCount: Field<Int> by lazy {
        Field(root.get("encryptDoneCount"), criteriaBuilder)
    }

    val failedCount: Field<Int> by lazy {
        Field(root.get("failedCount"), criteriaBuilder)
    }

    val lastFailReason: Field<String> by lazy {
        Field(root.get("lastFailReason"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SVideoPostProcessing>): Predicate {
        return builder.build(this)
    }
}
