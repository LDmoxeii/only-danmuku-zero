package edu.only4.danmuku.domain.aggregates.category.factory

import java.util.UUID

import com.only4.cap4k.ddd.core.domain.aggregate.AggregateFactory
import com.only4.cap4k.ddd.core.domain.aggregate.AggregatePayload
import com.only4.cap4k.ddd.core.domain.aggregate.annotation.Aggregate

import edu.only4.danmuku.domain.aggregates.category.Category

import org.springframework.stereotype.Service

/**
 * 分类信息;
 *
 * 本文件由[cap4k-ddd-codegen-gradle-plugin]生成
 * @author cap4k-ddd-codegen
 * @date 2025/10/15
 */
@Service
@Aggregate(
    aggregate = "Category",
    name = "CategoryFactory",
    type = Aggregate.TYPE_FACTORY,
    description = ""
)
class CategoryFactory : AggregateFactory<CategoryFactory.Payload, Category> {

    override fun create(entityPayload: Payload): Category {
        return Category(
            id = entityPayload.id,
            parentId = entityPayload.parentId,
            nodePath = "",
            sort = entityPayload.sort.toInt(),
            code = entityPayload.code,
            name = entityPayload.name,
            icon = entityPayload.icon,
            background = entityPayload.background,
            createUserId = null,
            createBy = null,
            createTime = null,
            updateUserId = null,
            updateBy = null,
            updateTime = null,
            deleted = 0L
        )
    }

     @Aggregate(
        aggregate = "Category",
        name = "CategoryPayload",
        type = Aggregate.TYPE_FACTORY_PAYLOAD,
        description = ""
    )
    data class Payload(
         /** 预分配分类ID */
         val id: UUID = UUID(0L, 0L),
         /** 父分类ID，顶级分类传0 */
         val parentId: UUID = UUID(0L, 0L),
         /** 分类编码，唯一标识 */
         val code: String,
         /** 分类名称 */
         val name: String,
         /** 图标路径或URL */
         val icon: String? = null,
         /** 背景图路径或URL */
         val background: String? = null,
         /** 排序号，同级分类中的显示顺序 */
         val sort: Byte = 0
    ) : AggregatePayload<Category>

}

