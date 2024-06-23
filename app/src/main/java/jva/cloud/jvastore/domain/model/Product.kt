package jva.cloud.jvastore.domain.model

import jva.cloud.jvastore.data.local.entity.ProductsEntity
import jva.cloud.jvastore.data.remote.respose.ProductsResponse
import jva.cloud.jvastore.util.ConstantApp

data class Product(
    val category: Category,
    val creationAt: String,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val title: String,
    val updatedAt: String,
    val selected: Boolean = ConstantApp.BOOLEAN_FALSE,
    val selectedQuantity: Int = ConstantApp.ZERO,
)

fun productsResponseToProduct(response: ProductsResponse): Product {
    return Product(
        id = response.id,
        creationAt = response.creationAt,
        price = response.price,
        title = response.title,
        images = response.images,
        category = categoryResponseToCategory(response = response.category),
        updatedAt = response.updatedAt,
        description = response.description
    )
}

fun listProductsResponseToListProduct(responses: List<ProductsResponse>): List<Product> {
    return responses.map { response ->
        Product(
            id = response.id,
            creationAt = response.creationAt,
            price = response.price,
            title = response.title,
            images = response.images,
            category = categoryResponseToCategory(response = response.category),
            updatedAt = response.updatedAt,
            description = response.description
        )
    }

}

fun productToProductsEntity(model: Product): ProductsEntity {
    return ProductsEntity(
        id = model.id,
        creationAt = model.creationAt,
        price = model.price,
        title = model.title,
        images = model.images,
        categoryId = model.category.id,
        updatedAt = model.updatedAt,
        description = model.description,
        selectedQuantity = model.selectedQuantity,
        selected = model.selected
    )
}

fun productsEntityToProduct(entity: ProductsEntity, entityCategory: Category): Product {
    return Product(
        id = entity.id,
        creationAt = entity.creationAt,
        price = entity.price,
        title = entity.title,
        images = entity.images,
        category = entityCategory,
        updatedAt = entity.updatedAt,
        description = entity.description,
        selected = entity.selected,
        selectedQuantity = entity.selectedQuantity
    )
}