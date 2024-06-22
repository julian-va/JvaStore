package jva.cloud.jvastore.data.local.repository

import jva.cloud.jvastore.data.local.entity.CategoryEntity

interface CategoryLocalRepository {
    suspend fun getAll(): List<CategoryEntity>

    suspend fun getById(idSearch: Int): CategoryEntity

    suspend fun saveAll(entities: List<CategoryEntity>)
}