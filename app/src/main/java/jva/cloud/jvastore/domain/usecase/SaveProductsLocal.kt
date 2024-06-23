package jva.cloud.jvastore.domain.usecase

import jva.cloud.jvastore.domain.model.Product

interface SaveProductsLocal {
    suspend fun saveAll(products: List<Product>)
}