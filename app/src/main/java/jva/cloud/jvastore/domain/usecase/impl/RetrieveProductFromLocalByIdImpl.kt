package jva.cloud.jvastore.domain.usecase.impl

import jva.cloud.jvastore.data.local.repository.CategoryLocalRepository
import jva.cloud.jvastore.data.local.repository.ProductsLocalRepository
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.domain.model.categoryEntityToCategory
import jva.cloud.jvastore.domain.model.productsEntityToProduct
import jva.cloud.jvastore.domain.usecase.RetrieveProductFromLocalById
import javax.inject.Inject

class RetrieveProductFromLocalByIdImpl @Inject constructor(
    private val productsLocalRepository: ProductsLocalRepository,
    private val categoryLocalRepository: CategoryLocalRepository
) : RetrieveProductFromLocalById {
    override suspend fun getById(id: Int): Product {
        val productEntity = productsLocalRepository.getById(idSearch = id)
        val categoryEntity = categoryLocalRepository.getById(idSearch = productEntity.categoryId)
        return productsEntityToProduct(
            entity = productEntity,
            entityCategory = categoryEntityToCategory(entity = categoryEntity)
        )
    }
}