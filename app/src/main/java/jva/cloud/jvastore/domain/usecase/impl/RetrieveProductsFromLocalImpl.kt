package jva.cloud.jvastore.domain.usecase.impl

import jva.cloud.jvastore.data.local.repository.CategoryLocalRepository
import jva.cloud.jvastore.data.local.repository.ProductsLocalRepository
import jva.cloud.jvastore.domain.model.Category
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.domain.model.categoryEntityToCategory
import jva.cloud.jvastore.domain.model.productsEntityToProduct
import jva.cloud.jvastore.domain.usecase.GetAllProducts
import jva.cloud.jvastore.domain.usecase.RetrieveProductsFromLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RetrieveProductsFromLocalImpl @Inject constructor(
    private val productsLocalRepository: ProductsLocalRepository,
    private val categoryLocalRepository: CategoryLocalRepository,
    private val getAllProducts: GetAllProducts
) :
    RetrieveProductsFromLocal {
    override fun getAllProducts(): Flow<List<Product>> {
        return productsLocalRepository.getAll().onEach { entities ->
            if (entities.isEmpty()) {
                getAllProducts.getAll()
            }
        }.map { entityList ->
            entityList.map { entity ->
                productsEntityToProduct(
                    entity = entity,
                    entityCategory = retrieveCategory(id = entity.categoryId)
                )
            }
        }
    }

    private suspend fun retrieveCategory(id: Int): Category {
        return categoryEntityToCategory(entity = categoryLocalRepository.getById(idSearch = id))
    }
}