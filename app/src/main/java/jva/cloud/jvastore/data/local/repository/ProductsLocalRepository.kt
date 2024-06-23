package jva.cloud.jvastore.data.local.repository

import jva.cloud.jvastore.data.local.entity.ProductsEntity
import kotlinx.coroutines.flow.Flow

interface ProductsLocalRepository {
    fun getAll(): Flow<List<ProductsEntity>>
    suspend fun getById(idSearch: Int): ProductsEntity
    suspend fun saveAll(entities: List<ProductsEntity>)
}