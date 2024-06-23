package jva.cloud.jvastore.domain.usecase.impl

import jva.cloud.jvastore.data.local.repository.ProductsLocalRepository
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.domain.model.productToProductsEntity
import jva.cloud.jvastore.domain.usecase.SaveProductsLocal
import javax.inject.Inject

class SaveProductsLocalImpl @Inject constructor(private val productsLocalRepository: ProductsLocalRepository) :
    SaveProductsLocal {
    override suspend fun saveAll(products: List<Product>) {
        productsLocalRepository.saveAll(entities = products.map { product ->
            productToProductsEntity(
                model = product
            )
        })
    }
}