package edu.only4.danmuku.domain._share.meta.category

import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePredicate
import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.category.AggCategory
import edu.only4.danmuku.domain.aggregates.category.Category
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SCategory(
    private val root: Path<Category>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val parentId = "parentId"

        val nodePath = "nodePath"

        val sort = "sort"

        val code = "code"

        val name = "name"

        val icon = "icon"

        val background = "background"

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
        fun specify(builder: PredicateBuilder<SCategory>): Specification<Category> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SCategory>, distinct: Boolean): Specification<Category> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SCategory>,
            vararg orderBuilders: OrderBuilder<SCategory>,
        ): Specification<Category> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SCategory>,
            orderBuilders: List<OrderBuilder<SCategory>>,
        ): Specification<Category> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SCategory>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SCategory>,
        ): Specification<Category> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SCategory>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SCategory>>,
        ): Specification<Category> {
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
        fun specify(specifier: SchemaSpecification<Category, SCategory>): Specification<Category> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SCategory(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SCategory, E>,
            predicateBuilder: PredicateBuilder<SCategory>,
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
            subqueryConfigure: SubqueryConfigure<E, SCategory>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(Category::class.java)
            val schema = SCategory(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): AggregatePredicate<AggCategory, Category> {
            return JpaPredicate.byId(Category::class.java, id).toAggregatePredicate(AggCategory::class.java)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): AggregatePredicate<AggCategory, Category> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(Category::class.java, ids as Iterable<Any>).toAggregatePredicate(AggCategory::class.java)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): AggregatePredicate<AggCategory, Category> {
            return JpaPredicate.byIds(Category::class.java, ids.toList()).toAggregatePredicate(AggCategory::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SCategory>): AggregatePredicate<AggCategory, Category> {
            return JpaPredicate.bySpecification(Category::class.java, specify(builder)).toAggregatePredicate(AggCategory::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SCategory>, distinct: Boolean): AggregatePredicate<AggCategory, Category> {
            return JpaPredicate.bySpecification(Category::class.java, specify(builder, distinct)).toAggregatePredicate(AggCategory::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SCategory>,
            orderBuilders: List<OrderBuilder<SCategory>>,
        ): AggregatePredicate<AggCategory, Category> {
            return JpaPredicate.bySpecification(Category::class.java, specify(builder, false, orderBuilders)).toAggregatePredicate(AggCategory::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SCategory>,
            vararg orderBuilders: OrderBuilder<SCategory>,
        ): AggregatePredicate<AggCategory, Category> {
            return JpaPredicate.bySpecification(Category::class.java, specify(builder, false, *orderBuilders)).toAggregatePredicate(AggCategory::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SCategory>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SCategory>>,
        ): AggregatePredicate<AggCategory, Category> {
            return JpaPredicate.bySpecification(Category::class.java, specify(builder, distinct, orderBuilders)).toAggregatePredicate(AggCategory::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SCategory>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SCategory>,
        ): AggregatePredicate<AggCategory, Category> {
            return JpaPredicate.bySpecification(Category::class.java, specify(builder, distinct, *orderBuilders)).toAggregatePredicate(AggCategory::class.java)
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<Category, SCategory>): AggregatePredicate<AggCategory, Category> {
            return JpaPredicate.bySpecification(Category::class.java, specify(specifier)).toAggregatePredicate(AggCategory::class.java)
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<Category> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val parentId: Field<Long> by lazy {
        Field(root.get("parentId"), criteriaBuilder)
    }

    val nodePath: Field<String> by lazy {
        Field(root.get("nodePath"), criteriaBuilder)
    }

    val sort: Field<Int> by lazy {
        Field(root.get("sort"), criteriaBuilder)
    }

    val code: Field<String> by lazy {
        Field(root.get("code"), criteriaBuilder)
    }

    val name: Field<String> by lazy {
        Field(root.get("name"), criteriaBuilder)
    }

    val icon: Field<String> by lazy {
        Field(root.get("icon"), criteriaBuilder)
    }

    val background: Field<String> by lazy {
        Field(root.get("background"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SCategory>): Predicate {
        return builder.build(this)
    }
}
