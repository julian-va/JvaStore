package jva.cloud.jvastore.domain.usecase

import jva.cloud.jvastore.domain.model.Product

interface RetrieveProductFromLocalById {
    suspend fun getById(id: Int): Product
}