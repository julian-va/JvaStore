package jva.cloud.jvastore.domain.usecase

import jva.cloud.jvastore.domain.model.Product

interface RetrieveProductById {
    suspend fun getProductById(id: String): Product?
}