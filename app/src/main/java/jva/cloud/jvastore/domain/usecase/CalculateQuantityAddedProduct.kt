package jva.cloud.jvastore.domain.usecase

import jva.cloud.jvastore.domain.model.Product

interface CalculateQuantityAddedProduct {
    suspend fun addQuantity(product: Product): Unit
    suspend fun removeQuantity(product: Product): Unit
    suspend fun amountToZero(products: List<Product>): Unit
}