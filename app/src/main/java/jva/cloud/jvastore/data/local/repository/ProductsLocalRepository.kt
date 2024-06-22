package jva.cloud.jvastore.data.local.repository

import jva.cloud.jvastore.data.local.entity.ProductsEntity

interface ProductsLocalRepository {
    suspend fun getAll(): List<ProductsEntity>
    suspend fun getById(idSearch: Int): ProductsEntity
    suspend fun saveAll(entities: List<ProductsEntity>)
}