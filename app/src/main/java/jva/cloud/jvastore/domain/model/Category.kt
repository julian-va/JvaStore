package jva.cloud.jvastore.domain.model

import jva.cloud.jvastore.data.local.entity.CategoryEntity
import jva.cloud.jvastore.data.remote.respose.CategoryResponse

data class Category(
    val creationAt: String,
    val id: Int,
    val image: String,
    val name: String,
    val updatedAt: String
)

fun categoryResponseToCategory(response: CategoryResponse): Category {
    return Category(
        updatedAt = response.updatedAt,
        id = response.id,
        name = response.name,
        image = response.image,
        creationAt = response.creationAt
    )
}

fun categoryEntityToCategory(entity: CategoryEntity): Category {
    return Category(
        updatedAt = entity.updatedAt,
        id = entity.id,
        name = entity.name,
        image = entity.image,
        creationAt = entity.creationAt
    )
}

fun categoryToCategoryEntity(model: Category): CategoryEntity {
    return CategoryEntity(
        updatedAt = model.updatedAt,
        id = model.id,
        name = model.name,
        image = model.image,
        creationAt = model.creationAt
    )
}