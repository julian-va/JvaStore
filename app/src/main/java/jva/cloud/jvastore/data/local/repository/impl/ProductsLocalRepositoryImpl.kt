package jva.cloud.jvastore.data.local.repository.impl

import jva.cloud.jvastore.data.local.dao.ProductsDao
import jva.cloud.jvastore.data.local.entity.ProductsEntity
import jva.cloud.jvastore.data.local.repository.ProductsLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsLocalRepositoryImpl @Inject constructor(private val productsDao: ProductsDao) :
    ProductsLocalRepository {
    override fun getAll(): Flow<List<ProductsEntity>> {
        return productsDao.getAll()
    }

    override suspend fun getById(idSearch: Int): ProductsEntity {
        return productsDao.getById(idSearch = idSearch)
    }

    override suspend fun saveAll(entities: List<ProductsEntity>) {
        productsDao.saveAll(entities = entities)
    }
}