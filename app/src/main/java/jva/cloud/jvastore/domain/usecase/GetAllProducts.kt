package jva.cloud.jvastore.domain.usecase

import jva.cloud.jvastore.domain.model.Product

interface GetAllProducts {
    suspend fun getAll(): List<Product>
}