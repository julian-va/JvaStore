package jva.cloud.jvastore.data.local.repository.impl

import jva.cloud.jvastore.data.local.dao.CategoryDao
import jva.cloud.jvastore.data.local.entity.CategoryEntity
import jva.cloud.jvastore.data.local.repository.CategoryLocalRepository
import javax.inject.Inject

class CategoryLocalRepositoryImpl @Inject constructor(private val categoryDao: CategoryDao) :
    CategoryLocalRepository {
    override suspend fun getAll(): List<CategoryEntity> {
        return categoryDao.getAll()
    }

    override suspend fun getById(idSearch: Int): CategoryEntity {
        return categoryDao.getById(idSearch = idSearch)
    }

    override suspend fun saveAll(entities: List<CategoryEntity>) {
        categoryDao.saveAll(entities = entities)
    }
}