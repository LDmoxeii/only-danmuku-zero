package edu.only4.danmuku.domain._share.meta.video_file_upload_session

import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.video_file_upload_session.VideoFileUploadSession
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SVideoFileUploadSession(
    private val root: Path<VideoFileUploadSession>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val customerId = "customerId"

        val fileName = "fileName"

        val chunks = "chunks"

        val chunkIndex = "chunkIndex"

        val fileSize = "fileSize"

        val tempDir = "tempDir"

        val status = "status"

        val duration = "duration"

        val createUserId = "createUserId"

        val createBy = "createBy"

        val createTime = "createTime"

        val updateUserId = "updateUserId"

        val updateBy = "updateBy"

        val updateTime = "updateTime"

        val expiresAt = "expiresAt"

        val deleted = "deleted"

    }

    companion object {

        val props = PROPERTY_NAMES()

        @JvmStatic
        fun specify(builder: PredicateBuilder<SVideoFileUploadSession>): Specification<VideoFileUploadSession> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SVideoFileUploadSession>, distinct: Boolean): Specification<VideoFileUploadSession> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoFileUploadSession>,
            vararg orderBuilders: OrderBuilder<SVideoFileUploadSession>,
        ): Specification<VideoFileUploadSession> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoFileUploadSession>,
            orderBuilders: List<OrderBuilder<SVideoFileUploadSession>>,
        ): Specification<VideoFileUploadSession> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoFileUploadSession>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideoFileUploadSession>,
        ): Specification<VideoFileUploadSession> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoFileUploadSession>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideoFileUploadSession>>,
        ): Specification<VideoFileUploadSession> {
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
        fun specify(specifier: SchemaSpecification<VideoFileUploadSession, SVideoFileUploadSession>): Specification<VideoFileUploadSession> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SVideoFileUploadSession(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SVideoFileUploadSession, E>,
            predicateBuilder: PredicateBuilder<SVideoFileUploadSession>,
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
            subqueryConfigure: SubqueryConfigure<E, SVideoFileUploadSession>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(VideoFileUploadSession::class.java)
            val schema = SVideoFileUploadSession(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): JpaPredicate<VideoFileUploadSession> {
            return JpaPredicate.byId(VideoFileUploadSession::class.java, id)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): JpaPredicate<VideoFileUploadSession> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(VideoFileUploadSession::class.java, ids as Iterable<Any>)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): JpaPredicate<VideoFileUploadSession> {
            return JpaPredicate.byIds(VideoFileUploadSession::class.java, ids.toList())
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SVideoFileUploadSession>): JpaPredicate<VideoFileUploadSession> {
            return JpaPredicate.bySpecification(VideoFileUploadSession::class.java, specify(builder))
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<VideoFileUploadSession, SVideoFileUploadSession>): JpaPredicate<VideoFileUploadSession> {
            return JpaPredicate.bySpecification(VideoFileUploadSession::class.java, specify(specifier))
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<VideoFileUploadSession> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val customerId: Field<Long> by lazy {
        Field(root.get("customerId"), criteriaBuilder)
    }

    val fileName: Field<String> by lazy {
        Field(root.get("fileName"), criteriaBuilder)
    }

    val chunks: Field<Int> by lazy {
        Field(root.get("chunks"), criteriaBuilder)
    }

    val chunkIndex: Field<Int> by lazy {
        Field(root.get("chunkIndex"), criteriaBuilder)
    }

    val fileSize: Field<Long> by lazy {
        Field(root.get("fileSize"), criteriaBuilder)
    }

    val tempDir: Field<String> by lazy {
        Field(root.get("tempDir"), criteriaBuilder)
    }

    val status: Field<edu.only4.danmuku.domain.aggregates.video_file_upload_session.enums.UploadStatus> by lazy {
        Field(root.get("status"), criteriaBuilder)
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

    val expiresAt: Field<Long> by lazy {
        Field(root.get("expiresAt"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SVideoFileUploadSession>): Predicate {
        return builder.build(this)
    }
}
