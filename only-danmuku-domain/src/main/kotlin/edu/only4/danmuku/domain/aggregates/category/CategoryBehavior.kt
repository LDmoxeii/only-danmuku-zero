package edu.only4.danmuku.domain.aggregates.category

import com.only4.cap4k.ddd.core.domain.event.DomainEventSupervisorSupport.events
import edu.only4.danmuku.domain.aggregates.category.events.CategoryBasicInfoUpdatedDomainEvent
import edu.only4.danmuku.domain.aggregates.category.events.CategoryCodeChangedDomainEvent
import edu.only4.danmuku.domain.aggregates.category.events.CategoryCreatedDomainEvent
import edu.only4.danmuku.domain.aggregates.category.events.CategoryDeletedDomainEvent
import edu.only4.danmuku.domain.aggregates.category.events.CategoryNodePathUpdatedDomainEvent
import edu.only4.danmuku.domain.aggregates.category.events.CategoryParentChangedDomainEvent
import edu.only4.danmuku.domain.aggregates.category.events.CategorySortChangedDomainEvent

fun Category.onCreate() {
    events().attach(this) { CategoryCreatedDomainEvent(entity = this, id = id) }
}

fun Category.onDelete() {
    events().attach(this) { CategoryDeletedDomainEvent(this) }
}

fun Category.updateBasicInfo(
    newName: String,
    newIcon: String?,
    newBackground: String?,
) {
    name = newName
    icon = newIcon
    background = newBackground
    events().attach(this) { CategoryBasicInfoUpdatedDomainEvent(this) }
}

fun Category.changeParent(newParentId: Long, parentCategory: Category?): Pair<String, String> {
    val oldPath = nodePath
    parentId = newParentId
    updateNodePath(parentCategory?.nodePath.orEmpty())
    events().attach(this) { CategoryParentChangedDomainEvent(this) }
    return oldPath to nodePath
}

fun Category.changeCode(newCode: String) {
    code = newCode
    events().attach(this) { CategoryCodeChangedDomainEvent(this) }
}

fun Category.isParentChanged(newParentId: Long): Boolean = parentId != newParentId

fun Category.isCodeChanged(newCode: String): Boolean = code != newCode

fun Category.isMovingToSelf(newParentId: Long): Boolean = newParentId == id

fun Category.isMovingToDescendant(parentCategory: Category): Boolean = parentCategory.nodePath.startsWith(nodePath)

fun Category.updateNodePath(parentPath: String = "") {
    nodePath = if (parentPath.isBlank()) {
        "/$id/"
    } else {
        "$parentPath$id/"
    }
    events().attach(this) { CategoryNodePathUpdatedDomainEvent(this) }
}

fun Category.addSort(increment: Int) {
    sort += increment
}

fun Category.isRoot(): Boolean = parentId == 0L

fun Category.getLevel(): Int = nodePath.trim('/').split('/').size

fun Category.getAncestorIds(): List<Long> =
    nodePath.trim('/').split('/').dropLast(1).mapNotNull { it.toLongOrNull() }

fun Category.isDescendantOf(ancestorId: Long): Boolean = nodePath.contains("/$ancestorId/")

fun Category.isDirectChildOf(parentId: Long): Boolean = this.parentId == parentId

fun Category.rebaseNodePath(oldPrefix: String, newPrefix: String) {
    if (nodePath.startsWith(oldPrefix)) {
        nodePath = nodePath.replaceFirst(oldPrefix, newPrefix)
    }
}

fun Category.changeSort(targetSort: Int) {
    sort = targetSort
    events().attach(this) { CategorySortChangedDomainEvent(this) }
}
