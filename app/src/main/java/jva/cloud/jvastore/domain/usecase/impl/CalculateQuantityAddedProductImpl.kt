package jva.cloud.jvastore.domain.usecase.impl

import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.domain.usecase.CalculateQuantityAddedProduct
import jva.cloud.jvastore.domain.usecase.SaveProductsLocal
import jva.cloud.jvastore.util.ConstantApp
import javax.inject.Inject

class CalculateQuantityAddedProductImpl @Inject constructor(private val saveProductsLocal: SaveProductsLocal) :
    CalculateQuantityAddedProduct {
    override suspend fun addQuantity(product: Product) {
        val quantity = product.selectedQuantity + ConstantApp.ONE
        val productDraft =
            product.copy(selectedQuantity = quantity, selected = quantity > ConstantApp.ZERO)
        saveProductsLocal.saveAll(products = listOf(productDraft))
    }

    override suspend fun removeQuantity(product: Product) {
        val quantity = product.selectedQuantity - ConstantApp.ONE
        val productDraft =
            product.copy(selectedQuantity = quantity, selected = quantity > ConstantApp.ZERO)
        saveProductsLocal.saveAll(products = listOf(productDraft))
    }

    override suspend fun amountToZero(products: List<Product>) {
        val productsDraft = products.map { product ->
            product.copy(
                selectedQuantity = ConstantApp.ZERO,
                selected = ConstantApp.BOOLEAN_FALSE
            )
        }
        saveProductsLocal.saveAll(products = productsDraft)
    }
}