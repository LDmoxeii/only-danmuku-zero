package edu.only4.danmuku.domain._share.meta.video_post_processing

import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.video_post_processing.VideoPostProcessingFile
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SVideoPostProcessingFile(
    private val root: Path<VideoPostProcessingFile>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val parentId = "parentId"

        val fileIndex = "fileIndex"

        val uploadId = "uploadId"

        val transcodeStatus = "transcodeStatus"

        val encryptStatus = "encryptStatus"

        val encryptMethod = "encryptMethod"

        val encryptKeyVersion = "encryptKeyVersion"

        val transcodeOutputPrefix = "transcodeOutputPrefix"

        val transcodeOutputPath = "transcodeOutputPath"

        val transcodeVariantsJson = "transcodeVariantsJson"

        val encryptOutputDir = "encryptOutputDir"

        val encryptOutputPrefix = "encryptOutputPrefix"

        val duration = "duration"

        val fileSize = "fileSize"

        val failReason = "failReason"

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
        fun specify(builder: PredicateBuilder<SVideoPostProcessingFile>): Specification<VideoPostProcessingFile> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SVideoPostProcessingFile>, distinct: Boolean): Specification<VideoPostProcessingFile> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoPostProcessingFile>,
            vararg orderBuilders: OrderBuilder<SVideoPostProcessingFile>,
        ): Specification<VideoPostProcessingFile> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoPostProcessingFile>,
            orderBuilders: List<OrderBuilder<SVideoPostProcessingFile>>,
        ): Specification<VideoPostProcessingFile> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoPostProcessingFile>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideoPostProcessingFile>,
        ): Specification<VideoPostProcessingFile> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoPostProcessingFile>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideoPostProcessingFile>>,
        ): Specification<VideoPostProcessingFile> {
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
        fun specify(specifier: SchemaSpecification<VideoPostProcessingFile, SVideoPostProcessingFile>): Specification<VideoPostProcessingFile> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SVideoPostProcessingFile(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SVideoPostProcessingFile, E>,
            predicateBuilder: PredicateBuilder<SVideoPostProcessingFile>,
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
            subqueryConfigure: SubqueryConfigure<E, SVideoPostProcessingFile>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(VideoPostProcessingFile::class.java)
            val schema = SVideoPostProcessingFile(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<VideoPostProcessingFile> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val parentId: Field<Long> by lazy {
        Field(root.get("parentId"), criteriaBuilder)
    }

    val fileIndex: Field<Int> by lazy {
        Field(root.get("fileIndex"), criteriaBuilder)
    }

    val uploadId: Field<Long> by lazy {
        Field(root.get("uploadId"), criteriaBuilder)
    }

    val transcodeStatus: Field<edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus> by lazy {
        Field(root.get("transcodeStatus"), criteriaBuilder)
    }

    val encryptStatus: Field<edu.only4.danmuku.domain.aggregates.video_post_processing.enums.ProcessStatus> by lazy {
        Field(root.get("encryptStatus"), criteriaBuilder)
    }

    val encryptMethod: Field<edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptMethod> by lazy {
        Field(root.get("encryptMethod"), criteriaBuilder)
    }

    val encryptKeyVersion: Field<Int> by lazy {
        Field(root.get("encryptKeyVersion"), criteriaBuilder)
    }

    val transcodeOutputPrefix: Field<String> by lazy {
        Field(root.get("transcodeOutputPrefix"), criteriaBuilder)
    }

    val transcodeOutputPath: Field<String> by lazy {
        Field(root.get("transcodeOutputPath"), criteriaBuilder)
    }

    val transcodeVariantsJson: Field<String> by lazy {
        Field(root.get("transcodeVariantsJson"), criteriaBuilder)
    }

    val encryptOutputDir: Field<String> by lazy {
        Field(root.get("encryptOutputDir"), criteriaBuilder)
    }

    val encryptOutputPrefix: Field<String> by lazy {
        Field(root.get("encryptOutputPrefix"), criteriaBuilder)
    }

    val duration: Field<Int> by lazy {
        Field(root.get("duration"), criteriaBuilder)
    }

    val fileSize: Field<Long> by lazy {
        Field(root.get("fileSize"), criteriaBuilder)
    }

    val failReason: Field<String> by lazy {
        Field(root.get("failReason"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SVideoPostProcessingFile>): Predicate {
        return builder.build(this)
    }
}
