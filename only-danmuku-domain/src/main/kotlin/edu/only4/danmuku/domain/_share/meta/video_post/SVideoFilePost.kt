package edu.only4.danmuku.domain._share.meta.video_post

import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.video_post.VideoFilePost
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SVideoFilePost(
    private val root: Path<VideoFilePost>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val videoPostId = "videoPostId"

        val uploadId = "uploadId"

        val customerId = "customerId"

        val fileIndex = "fileIndex"

        val fileName = "fileName"

        val fileSize = "fileSize"

        val transcodeOutputPrefix = "transcodeOutputPrefix"

        val encryptOutputPrefix = "encryptOutputPrefix"

        val transferResult = "transferResult"

        val encryptStatus = "encryptStatus"

        val encryptMethod = "encryptMethod"

        val encryptKeyVersion = "encryptKeyVersion"

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
        fun specify(builder: PredicateBuilder<SVideoFilePost>): Specification<VideoFilePost> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SVideoFilePost>, distinct: Boolean): Specification<VideoFilePost> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoFilePost>,
            vararg orderBuilders: OrderBuilder<SVideoFilePost>,
        ): Specification<VideoFilePost> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoFilePost>,
            orderBuilders: List<OrderBuilder<SVideoFilePost>>,
        ): Specification<VideoFilePost> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoFilePost>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideoFilePost>,
        ): Specification<VideoFilePost> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoFilePost>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideoFilePost>>,
        ): Specification<VideoFilePost> {
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
        fun specify(specifier: SchemaSpecification<VideoFilePost, SVideoFilePost>): Specification<VideoFilePost> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SVideoFilePost(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SVideoFilePost, E>,
            predicateBuilder: PredicateBuilder<SVideoFilePost>,
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
            subqueryConfigure: SubqueryConfigure<E, SVideoFilePost>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(VideoFilePost::class.java)
            val schema = SVideoFilePost(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<VideoFilePost> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val videoPostId: Field<Long> by lazy {
        Field(root.get("videoPostId"), criteriaBuilder)
    }

    val uploadId: Field<Long> by lazy {
        Field(root.get("uploadId"), criteriaBuilder)
    }

    val customerId: Field<Long> by lazy {
        Field(root.get("customerId"), criteriaBuilder)
    }

    val fileIndex: Field<Int> by lazy {
        Field(root.get("fileIndex"), criteriaBuilder)
    }

    val fileName: Field<String> by lazy {
        Field(root.get("fileName"), criteriaBuilder)
    }

    val fileSize: Field<Long> by lazy {
        Field(root.get("fileSize"), criteriaBuilder)
    }

    val transcodeOutputPrefix: Field<String> by lazy {
        Field(root.get("transcodeOutputPrefix"), criteriaBuilder)
    }

    val encryptOutputPrefix: Field<String> by lazy {
        Field(root.get("encryptOutputPrefix"), criteriaBuilder)
    }

    val transferResult: Field<edu.only4.danmuku.domain.aggregates.video_post.enums.TransferResult> by lazy {
        Field(root.get("transferResult"), criteriaBuilder)
    }

    val encryptStatus: Field<edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptStatus> by lazy {
        Field(root.get("encryptStatus"), criteriaBuilder)
    }

    val encryptMethod: Field<edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptMethod> by lazy {
        Field(root.get("encryptMethod"), criteriaBuilder)
    }

    val encryptKeyVersion: Field<Int> by lazy {
        Field(root.get("encryptKeyVersion"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SVideoFilePost>): Predicate {
        return builder.build(this)
    }
}
