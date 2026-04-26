package edu.only4.danmuku.domain._share.meta.customer_action

import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePredicate
import com.only4.cap4k.ddd.domain.repo.JpaPredicate
import com.only4.cap4k.ddd.domain.repo.schema.ExpressionBuilder
import com.only4.cap4k.ddd.domain.repo.schema.Field
import com.only4.cap4k.ddd.domain.repo.schema.OrderBuilder
import com.only4.cap4k.ddd.domain.repo.schema.PredicateBuilder
import com.only4.cap4k.ddd.domain.repo.schema.SchemaSpecification
import com.only4.cap4k.ddd.domain.repo.schema.SubqueryConfigure
import edu.only4.danmuku.domain.aggregates.customer_action.AggCustomerAction
import edu.only4.danmuku.domain.aggregates.customer_action.CustomerAction
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Subquery
import org.springframework.data.jpa.domain.Specification

class SCustomerAction(
    private val root: Path<CustomerAction>,
    private val criteriaBuilder: CriteriaBuilder,
) {
    class PROPERTY_NAMES {

        val id = "id"

        val customerId = "customerId"

        val videoId = "videoId"

        val videoOwnerId = "videoOwnerId"

        val commentId = "commentId"

        val actionType = "actionType"

        val actionCount = "actionCount"

        val actionTime = "actionTime"

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
        fun specify(builder: PredicateBuilder<SCustomerAction>): Specification<CustomerAction> {
            return specify(builder, false, emptyList())
        }

        @JvmStatic
        fun specify(builder: PredicateBuilder<SCustomerAction>, distinct: Boolean): Specification<CustomerAction> {
            return specify(builder, distinct, emptyList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SCustomerAction>,
            vararg orderBuilders: OrderBuilder<SCustomerAction>,
        ): Specification<CustomerAction> {
            return specify(builder, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SCustomerAction>,
            orderBuilders: List<OrderBuilder<SCustomerAction>>,
        ): Specification<CustomerAction> {
            return specify(builder, false, orderBuilders)
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SCustomerAction>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SCustomerAction>,
        ): Specification<CustomerAction> {
            return specify(builder, distinct, orderBuilders.toList())
        }

        @JvmStatic
        fun specify(
            builder: PredicateBuilder<SCustomerAction>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SCustomerAction>>,
        ): Specification<CustomerAction> {
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
        fun specify(specifier: SchemaSpecification<CustomerAction, SCustomerAction>): Specification<CustomerAction> {
            return Specification { root, criteriaQuery, criteriaBuilder ->
                val schema = SCustomerAction(root, criteriaBuilder)
                specifier.toPredicate(schema, criteriaQuery, criteriaBuilder)
            }
        }

        @JvmStatic
        fun <E> subquery(
            resultClass: Class<E>,
            selectBuilder: ExpressionBuilder<SCustomerAction, E>,
            predicateBuilder: PredicateBuilder<SCustomerAction>,
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
            subqueryConfigure: SubqueryConfigure<E, SCustomerAction>,
            criteriaBuilder: CriteriaBuilder,
            criteriaQuery: CriteriaQuery<*>,
        ): Subquery<E> {
            val sq = criteriaQuery.subquery(resultClass)
            val root = sq.from(CustomerAction::class.java)
            val schema = SCustomerAction(root, criteriaBuilder)
            subqueryConfigure.configure(sq, schema)
            return sq
        }

        @JvmStatic
        fun predicateById(id: Any): AggregatePredicate<AggCustomerAction, CustomerAction> {
            return JpaPredicate.byId(CustomerAction::class.java, id).toAggregatePredicate(AggCustomerAction::class.java)
        }

        @JvmStatic
        fun predicateByIds(ids: Iterable<*>): AggregatePredicate<AggCustomerAction, CustomerAction> {
            @Suppress("UNCHECKED_CAST")
            return JpaPredicate.byIds(CustomerAction::class.java, ids as Iterable<Any>).toAggregatePredicate(AggCustomerAction::class.java)
        }

        @JvmStatic
        fun predicateByIds(vararg ids: Any): AggregatePredicate<AggCustomerAction, CustomerAction> {
            return JpaPredicate.byIds(CustomerAction::class.java, ids.toList()).toAggregatePredicate(AggCustomerAction::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SCustomerAction>): AggregatePredicate<AggCustomerAction, CustomerAction> {
            return JpaPredicate.bySpecification(CustomerAction::class.java, specify(builder)).toAggregatePredicate(AggCustomerAction::class.java)
        }

        @JvmStatic
        fun predicate(builder: PredicateBuilder<SCustomerAction>, distinct: Boolean): AggregatePredicate<AggCustomerAction, CustomerAction> {
            return JpaPredicate.bySpecification(CustomerAction::class.java, specify(builder, distinct)).toAggregatePredicate(AggCustomerAction::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SCustomerAction>,
            orderBuilders: List<OrderBuilder<SCustomerAction>>,
        ): AggregatePredicate<AggCustomerAction, CustomerAction> {
            return JpaPredicate.bySpecification(CustomerAction::class.java, specify(builder, false, orderBuilders)).toAggregatePredicate(AggCustomerAction::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SCustomerAction>,
            vararg orderBuilders: OrderBuilder<SCustomerAction>,
        ): AggregatePredicate<AggCustomerAction, CustomerAction> {
            return JpaPredicate.bySpecification(CustomerAction::class.java, specify(builder, false, *orderBuilders)).toAggregatePredicate(AggCustomerAction::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SCustomerAction>,
            distinct: Boolean,
            orderBuilders: List<OrderBuilder<SCustomerAction>>,
        ): AggregatePredicate<AggCustomerAction, CustomerAction> {
            return JpaPredicate.bySpecification(CustomerAction::class.java, specify(builder, distinct, orderBuilders)).toAggregatePredicate(AggCustomerAction::class.java)
        }

        @JvmStatic
        fun predicate(
            builder: PredicateBuilder<SCustomerAction>,
            distinct: Boolean,
            vararg orderBuilders: OrderBuilder<SCustomerAction>,
        ): AggregatePredicate<AggCustomerAction, CustomerAction> {
            return JpaPredicate.bySpecification(CustomerAction::class.java, specify(builder, distinct, *orderBuilders)).toAggregatePredicate(AggCustomerAction::class.java)
        }

        @JvmStatic
        fun predicate(specifier: SchemaSpecification<CustomerAction, SCustomerAction>): AggregatePredicate<AggCustomerAction, CustomerAction> {
            return JpaPredicate.bySpecification(CustomerAction::class.java, specify(specifier)).toAggregatePredicate(AggCustomerAction::class.java)
        }
    }

    fun _criteriaBuilder(): CriteriaBuilder = criteriaBuilder

    fun _root(): Path<CustomerAction> = root

    val id: Field<Long> by lazy {
        Field(root.get("id"), criteriaBuilder)
    }

    val customerId: Field<Long> by lazy {
        Field(root.get("customerId"), criteriaBuilder)
    }

    val videoId: Field<Long> by lazy {
        Field(root.get("videoId"), criteriaBuilder)
    }

    val videoOwnerId: Field<Long> by lazy {
        Field(root.get("videoOwnerId"), criteriaBuilder)
    }

    val commentId: Field<Long> by lazy {
        Field(root.get("commentId"), criteriaBuilder)
    }

    val actionType: Field<edu.only4.danmuku.domain.aggregates.customer_action.enums.ActionType> by lazy {
        Field(root.get("actionType"), criteriaBuilder)
    }

    val actionCount: Field<Int> by lazy {
        Field(root.get("actionCount"), criteriaBuilder)
    }

    val actionTime: Field<Long> by lazy {
        Field(root.get("actionTime"), criteriaBuilder)
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

    fun spec(builder: PredicateBuilder<SCustomerAction>): Predicate {
        return builder.build(this)
    }
}
