package jva.cloud.jvastore.domain.usecase

import jva.cloud.jvastore.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface RetrieveProductsFromLocal {
    fun getAllProducts(): Flow<List<Product>>
}