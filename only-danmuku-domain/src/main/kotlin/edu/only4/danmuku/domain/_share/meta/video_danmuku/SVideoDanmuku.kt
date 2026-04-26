package edu.only4.danmuku.domain._share.meta.video_danmuku

import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.video_danmuku.VideoDanmuku
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SVideoDanmuku(
    private val root: Path<VideoDanmuku>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val videoId = "videoId"

        val fileId = "fileId"

        val customerId = "customerId"

        val postTime = "postTime"

        val text = "text"

        val mode = "mode"

        val color = "color"

        val time = "time"

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
        fun specify(builder: PredicateBuilder<SVideoDanmuku>): Specification<VideoDanmuku> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SVideoDanmuku>, distinct: Boolean): Specification<VideoDanmuku> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoDanmuku>,
            vararg orderBuilders: OrderBuilder<SVideoDanmuku>,
        ): Specification<VideoDanmuku> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoDanmuku>,
            orderBuilders: List<OrderBuilder<SVideoDanmuku>>,
        ): Specification<VideoDanmuku> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoDanmuku>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SVideoDanmuku>,
        ): Specification<VideoDanmuku> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SVideoDanmuku>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SVideoDanmuku>>,
        ): Specification<VideoDanmuku> {
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
        fun specify(specifier: SchemaSpecification<VideoDanmuku, SVideoDanmuku>): Specification<VideoDanmuku> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SVideoDanmuku(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SVideoDanmuku, E>,
            predicateBuilder: PredicateBuilder<SVideoDanmuku>,
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
            subqueryConfigure: SubqueryConfigure<E, SVideoDanmuku>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(VideoDanmuku::class.java)
            val schema = SVideoDanmuku(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): JpaPredicate<VideoDanmuku> {
            return JpaPredicate.byId(VideoDanmuku::class.java, id)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): JpaPredicate<VideoDanmuku> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(VideoDanmuku::class.java, ids as Iterable<Any>)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): JpaPredicate<VideoDanmuku> {
            return JpaPredicate.byIds(VideoDanmuku::class.java, ids.toList())
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SVideoDanmuku>): JpaPredicate<VideoDanmuku> {
            return JpaPredicate.bySpecification(VideoDanmuku::class.java, specify(builder))
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<VideoDanmuku, SVideoDanmuku>): JpaPredicate<VideoDanmuku> {
            return JpaPredicate.bySpecification(VideoDanmuku::class.java, specify(specifier))
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<VideoDanmuku> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val videoId: Field<Long> by lazy {
        Field(root.get("videoId"), criteriaBuilder)
    }

    val fileId: Field<Long> by lazy {
        Field(root.get("fileId"), criteriaBuilder)
    }

    val customerId: Field<Long> by lazy {
        Field(root.get("customerId"), criteriaBuilder)
    }

    val postTime: Field<Long> by lazy {
        Field(root.get("postTime"), criteriaBuilder)
    }

    val text: Field<String> by lazy {
        Field(root.get("text"), criteriaBuilder)
    }

    val mode: Field<Int> by lazy {
        Field(root.get("mode"), criteriaBuilder)
    }

    val color: Field<String> by lazy {
        Field(root.get("color"), criteriaBuilder)
    }

    val time: Field<Int> by lazy {
        Field(root.get("time"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SVideoDanmuku>): Predicate {
        return builder.build(this)
    }
}
