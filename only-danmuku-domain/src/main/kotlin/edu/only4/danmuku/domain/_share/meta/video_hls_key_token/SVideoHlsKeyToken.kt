package edu.only4.danmuku.domain._share.meta.video_hls_key_token

import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.video_hls_key_token.VideoHlsKeyToken
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SVideoHlsKeyToken(
    private val root: Path<VideoHlsKeyToken>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val videoPostId = "videoPostId"

        val videoId = "videoId"

        val fileIndex = "fileIndex"

        val keyVersion = "keyVersion"

        val allowedQualities = "allowedQualities"

        val tokenHash = "tokenHash"

        val audience = "audience"

        val expireTime = "expireTime"

        val maxUse = "maxUse"

        val usedCount = "usedCount"

        val status = "status"

        val issueIp = "issueIp"

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
        fun specify(builder: PredicateBuilder<SVideoHlsKeyToken>): Specification<VideoHlsKeyToken> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SVideoHlsKeyToken>, distinct: Boolean): Specification<VideoHlsKeyToken> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoHlsKeyToken>,
            vararg orderBuilders: OrderBuilder<SVideoHlsKeyToken>,
        ): Specification<VideoHlsKeyToken> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoHlsKeyToken>,
            orderBuilders: List<OrderBuilder<SVideoHlsKeyToken>>,
        ): Specification<VideoHlsKeyToken> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoHlsKeyToken>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideoHlsKeyToken>,
        ): Specification<VideoHlsKeyToken> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoHlsKeyToken>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideoHlsKeyToken>>,
        ): Specification<VideoHlsKeyToken> {
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
        fun specify(specifier: SchemaSpecification<VideoHlsKeyToken, SVideoHlsKeyToken>): Specification<VideoHlsKeyToken> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SVideoHlsKeyToken(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SVideoHlsKeyToken, E>,
            predicateBuilder: PredicateBuilder<SVideoHlsKeyToken>,
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
            subqueryConfigure: SubqueryConfigure<E, SVideoHlsKeyToken>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(VideoHlsKeyToken::class.java)
            val schema = SVideoHlsKeyToken(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): JpaPredicate<VideoHlsKeyToken> {
            return JpaPredicate.byId(VideoHlsKeyToken::class.java, id)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): JpaPredicate<VideoHlsKeyToken> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(VideoHlsKeyToken::class.java, ids as Iterable<Any>)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): JpaPredicate<VideoHlsKeyToken> {
            return JpaPredicate.byIds(VideoHlsKeyToken::class.java, ids.toList())
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SVideoHlsKeyToken>): JpaPredicate<VideoHlsKeyToken> {
            return JpaPredicate.bySpecification(VideoHlsKeyToken::class.java, specify(builder))
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<VideoHlsKeyToken, SVideoHlsKeyToken>): JpaPredicate<VideoHlsKeyToken> {
            return JpaPredicate.bySpecification(VideoHlsKeyToken::class.java, specify(specifier))
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<VideoHlsKeyToken> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val videoPostId: Field<Long> by lazy {
        Field(root.get("videoPostId"), criteriaBuilder)
    }

    val videoId: Field<Long> by lazy {
        Field(root.get("videoId"), criteriaBuilder)
    }

    val fileIndex: Field<Int> by lazy {
        Field(root.get("fileIndex"), criteriaBuilder)
    }

    val keyVersion: Field<Int> by lazy {
        Field(root.get("keyVersion"), criteriaBuilder)
    }

    val allowedQualities: Field<String> by lazy {
        Field(root.get("allowedQualities"), criteriaBuilder)
    }

    val tokenHash: Field<String> by lazy {
        Field(root.get("tokenHash"), criteriaBuilder)
    }

    val audience: Field<String> by lazy {
        Field(root.get("audience"), criteriaBuilder)
    }

    val expireTime: Field<Long> by lazy {
        Field(root.get("expireTime"), criteriaBuilder)
    }

    val maxUse: Field<Int> by lazy {
        Field(root.get("maxUse"), criteriaBuilder)
    }

    val usedCount: Field<Int> by lazy {
        Field(root.get("usedCount"), criteriaBuilder)
    }

    val status: Field<edu.only4.danmuku.domain.aggregates.video_hls_key_token.enums.EncryptTokenStatus> by lazy {
        Field(root.get("status"), criteriaBuilder)
    }

    val issueIp: Field<String> by lazy {
        Field(root.get("issueIp"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SVideoHlsKeyToken>): Predicate {
        return builder.build(this)
    }
}
