package edu.only4.danmuku.domain._share.meta.video_hls_encrypt_key

import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePredicate
import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.AggVideoHlsEncryptKey
import edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.VideoHlsEncryptKey
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SVideoHlsEncryptKey(
    private val root: Path<VideoHlsEncryptKey>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val videoPostId = "videoPostId"

        val videoId = "videoId"

        val fileIndex = "fileIndex"

        val quality = "quality"

        val keyId = "keyId"

        val keyCiphertext = "keyCiphertext"

        val ivHex = "ivHex"

        val keyVersion = "keyVersion"

        val method = "method"

        val keyUriTemplate = "keyUriTemplate"

        val expireTime = "expireTime"

        val status = "status"

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
        fun specify(builder: PredicateBuilder<SVideoHlsEncryptKey>): Specification<VideoHlsEncryptKey> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SVideoHlsEncryptKey>, distinct: Boolean): Specification<VideoHlsEncryptKey> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoHlsEncryptKey>,
            vararg orderBuilders: OrderBuilder<SVideoHlsEncryptKey>,
        ): Specification<VideoHlsEncryptKey> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoHlsEncryptKey>,
            orderBuilders: List<OrderBuilder<SVideoHlsEncryptKey>>,
        ): Specification<VideoHlsEncryptKey> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoHlsEncryptKey>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideoHlsEncryptKey>,
        ): Specification<VideoHlsEncryptKey> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoHlsEncryptKey>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideoHlsEncryptKey>>,
        ): Specification<VideoHlsEncryptKey> {
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
        fun specify(specifier: SchemaSpecification<VideoHlsEncryptKey, SVideoHlsEncryptKey>): Specification<VideoHlsEncryptKey> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SVideoHlsEncryptKey(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SVideoHlsEncryptKey, E>,
            predicateBuilder: PredicateBuilder<SVideoHlsEncryptKey>,
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
            subqueryConfigure: SubqueryConfigure<E, SVideoHlsEncryptKey>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(VideoHlsEncryptKey::class.java)
            val schema = SVideoHlsEncryptKey(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): AggregatePredicate<AggVideoHlsEncryptKey, VideoHlsEncryptKey> {
            return JpaPredicate.byId(VideoHlsEncryptKey::class.java, id).toAggregatePredicate(AggVideoHlsEncryptKey::class.java)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): AggregatePredicate<AggVideoHlsEncryptKey, VideoHlsEncryptKey> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(VideoHlsEncryptKey::class.java, ids as Iterable<Any>).toAggregatePredicate(AggVideoHlsEncryptKey::class.java)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): AggregatePredicate<AggVideoHlsEncryptKey, VideoHlsEncryptKey> {
            return JpaPredicate.byIds(VideoHlsEncryptKey::class.java, ids.toList()).toAggregatePredicate(AggVideoHlsEncryptKey::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SVideoHlsEncryptKey>): AggregatePredicate<AggVideoHlsEncryptKey, VideoHlsEncryptKey> {
            return JpaPredicate.bySpecification(VideoHlsEncryptKey::class.java, specify(builder)).toAggregatePredicate(AggVideoHlsEncryptKey::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SVideoHlsEncryptKey>, distinct: Boolean): AggregatePredicate<AggVideoHlsEncryptKey, VideoHlsEncryptKey> {
            return JpaPredicate.bySpecification(VideoHlsEncryptKey::class.java, specify(builder, distinct)).toAggregatePredicate(AggVideoHlsEncryptKey::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideoHlsEncryptKey>,
            orderBuilders: List<OrderBuilder<SVideoHlsEncryptKey>>,
        ): AggregatePredicate<AggVideoHlsEncryptKey, VideoHlsEncryptKey> {
            return JpaPredicate.bySpecification(VideoHlsEncryptKey::class.java, specify(builder, false, orderBuilders)).toAggregatePredicate(AggVideoHlsEncryptKey::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideoHlsEncryptKey>,
            vararg orderBuilders: OrderBuilder<SVideoHlsEncryptKey>,
        ): AggregatePredicate<AggVideoHlsEncryptKey, VideoHlsEncryptKey> {
            return JpaPredicate.bySpecification(VideoHlsEncryptKey::class.java, specify(builder, false, *orderBuilders)).toAggregatePredicate(AggVideoHlsEncryptKey::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideoHlsEncryptKey>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideoHlsEncryptKey>>,
        ): AggregatePredicate<AggVideoHlsEncryptKey, VideoHlsEncryptKey> {
            return JpaPredicate.bySpecification(VideoHlsEncryptKey::class.java, specify(builder, distinct, orderBuilders)).toAggregatePredicate(AggVideoHlsEncryptKey::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SVideoHlsEncryptKey>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideoHlsEncryptKey>,
        ): AggregatePredicate<AggVideoHlsEncryptKey, VideoHlsEncryptKey> {
            return JpaPredicate.bySpecification(VideoHlsEncryptKey::class.java, specify(builder, distinct, *orderBuilders)).toAggregatePredicate(AggVideoHlsEncryptKey::class.java)
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<VideoHlsEncryptKey, SVideoHlsEncryptKey>): AggregatePredicate<AggVideoHlsEncryptKey, VideoHlsEncryptKey> {
            return JpaPredicate.bySpecification(VideoHlsEncryptKey::class.java, specify(specifier)).toAggregatePredicate(AggVideoHlsEncryptKey::class.java)
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<VideoHlsEncryptKey> = root

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

    val quality: Field<String> by lazy {
        Field(root.get("quality"), criteriaBuilder)
    }

    val keyId: Field<String> by lazy {
        Field(root.get("keyId"), criteriaBuilder)
    }

    val keyCiphertext: Field<String> by lazy {
        Field(root.get("keyCiphertext"), criteriaBuilder)
    }

    val ivHex: Field<String> by lazy {
        Field(root.get("ivHex"), criteriaBuilder)
    }

    val keyVersion: Field<Int> by lazy {
        Field(root.get("keyVersion"), criteriaBuilder)
    }

    val method: Field<edu.only4.danmuku.domain.aggregates.video_post.enums.EncryptMethod> by lazy {
        Field(root.get("method"), criteriaBuilder)
    }

    val keyUriTemplate: Field<String> by lazy {
        Field(root.get("keyUriTemplate"), criteriaBuilder)
    }

    val expireTime: Field<Long> by lazy {
        Field(root.get("expireTime"), criteriaBuilder)
    }

    val status: Field<edu.only4.danmuku.domain.aggregates.video_hls_encrypt_key.enums.EncryptKeyStatus> by lazy {
        Field(root.get("status"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SVideoHlsEncryptKey>): Predicate {
        return builder.build(this)
    }
}
