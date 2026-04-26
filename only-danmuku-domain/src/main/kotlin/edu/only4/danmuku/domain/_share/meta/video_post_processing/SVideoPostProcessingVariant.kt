package edu.only4.danmuku.domain._share.meta.video_post_processing

import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.video_post_processing.VideoPostProcessingVariant
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SVideoPostProcessingVariant(
    private val root: Path<VideoPostProcessingVariant>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val parentId = "parentId"

        val quality = "quality"

        val width = "width"

        val height = "height"

        val videoBitrateKbps = "videoBitrateKbps"

        val audioBitrateKbps = "audioBitrateKbps"

        val bandwidthBps = "bandwidthBps"

        val playlistPath = "playlistPath"

        val segmentPrefix = "segmentPrefix"

        val segmentDuration = "segmentDuration"

        val transcodeStatus = "transcodeStatus"

        val encryptStatus = "encryptStatus"

        val encryptFailReason = "encryptFailReason"

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
        fun specify(builder: PredicateBuilder<SVideoPostProcessingVariant>): Specification<VideoPostProcessingVariant> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SVideoPostProcessingVariant>, distinct: Boolean): Specification<VideoPostProcessingVariant> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoPostProcessingVariant>,
            vararg orderBuilders: OrderBuilder<SVideoPostProcessingVariant>,
        ): Specification<VideoPostProcessingVariant> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoPostProcessingVariant>,
            orderBuilders: List<OrderBuilder<SVideoPostProcessingVariant>>,
        ): Specification<VideoPostProcessingVariant> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoPostProcessingVariant>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideoPostProcessingVariant>,
        ): Specification<VideoPostProcessingVariant> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoPostProcessingVariant>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideoPostProcessingVariant>>,
        ): Specification<VideoPostProcessingVariant> {
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
        fun specify(specifier: SchemaSpecification<VideoPostProcessingVariant, SVideoPostProcessingVariant>): Specification<VideoPostProcessingVariant> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SVideoPostProcessingVariant(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SVideoPostProcessingVariant, E>,
            predicateBuilder: PredicateBuilder<SVideoPostProcessingVariant>,
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
            subqueryConfigure: SubqueryConfigure<E, SVideoPostProcessingVariant>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(VideoPostProcessingVariant::class.java)
            val schema = SVideoPostProcessingVariant(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<VideoPostProcessingVariant> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val parentId: Field<Long> by lazy {
        Field(root.get("parentId"), criteriaBuilder)
    }

    val quality: Field<String> by lazy {
        Field(root.get("quality"), criteriaBuilder)
    }

    val width: Field<Int> by lazy {
        Field(root.get("width"), criteriaBuilder)
    }

    val height: Field<Int> by lazy {
        Field(root.get("height"), criteriaBuilder)
    }

    val videoBitrateKbps: Field<Int> by lazy {
        Field(root.get("videoBitrateKbps"), criteriaBuilder)
    }

    val audioBitrateKbps: Field<Int> by lazy {
        Field(root.get("audioBitrateKbps"), criteriaBuilder)
    }

    val bandwidthBps: Field<Int> by lazy {
        Field(root.get("bandwidthBps"), criteriaBuilder)
    }

    val playlistPath: Field<String> by lazy {
        Field(root.get("playlistPath"), criteriaBuilder)
    }

    val segmentPrefix: Field<String> by lazy {
        Field(root.get("segmentPrefix"), criteriaBuilder)
    }

    val segmentDuration: Field<Int> by lazy {
        Field(root.get("segmentDuration"), criteriaBuilder)
    }

    val transcodeStatus: Field<edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus> by lazy {
        Field(root.get("transcodeStatus"), criteriaBuilder)
    }

    val encryptStatus: Field<edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus> by lazy {
        Field(root.get("encryptStatus"), criteriaBuilder)
    }

    val encryptFailReason: Field<String> by lazy {
        Field(root.get("encryptFailReason"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SVideoPostProcessingVariant>): Predicate {
        return builder.build(this)
    }
}
