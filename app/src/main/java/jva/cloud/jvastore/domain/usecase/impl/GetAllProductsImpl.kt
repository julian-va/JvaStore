package jva.cloud.jvastore.domain.usecase.impl

import jva.cloud.jvastore.data.local.entity.CategoryEntity
import jva.cloud.jvastore.data.local.entity.ProductsEntity
import jva.cloud.jvastore.data.local.repository.CategoryLocalRepository
import jva.cloud.jvastore.data.local.repository.ProductsLocalRepository
import jva.cloud.jvastore.data.remote.repository.ProductsRepository
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.domain.model.categoryToCategoryEntity
import jva.cloud.jvastore.domain.model.listProductsResponseToListProduct
import jva.cloud.jvastore.domain.model.productToProductsEntity
import jva.cloud.jvastore.domain.usecase.GetAllProducts
import javax.inject.Inject

class GetAllProductsImpl @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val productsLocalRepository: ProductsLocalRepository,
    private val categoryLocalRepository: CategoryLocalRepository
) :
    GetAllProducts {
    override suspend fun getAll(): List<Product> {

        val listProduct: List<Product> = (1..10).map { _ -> getProductionApi() }.flatten()
        saveResponseToDataBase(listProduct = listProduct)
        return listProduct
    }

    private suspend fun getProductionApi(): List<Product> {
        var listProduct: List<Product> = listOf()
        try {
            val response = productsRepository.getAllProducts()
            if (response.isSuccessful) {
                response.body()
                    ?.let { productsResponses ->
                        listProduct =
                            listProductsResponseToListProduct(responses = productsResponses)
                    }
            }
        } catch (ex: Exception) {
            println("failed to users recovered to the server, the error is: ${ex.message}")
        }
        return listProduct
    }

    private suspend fun saveResponseToDataBase(listProduct: List<Product>) {
        val productsEntityList: MutableList<ProductsEntity> = mutableListOf()
        val categoryEntityList: MutableList<CategoryEntity> = mutableListOf()

        listProduct.forEach { product ->
            productsEntityList.add(productToProductsEntity(model = product))
            categoryEntityList.add(categoryToCategoryEntity(model = product.category))
        }
        productsLocalRepository.saveAll(entities = productsEntityList)
        categoryLocalRepository.saveAll(entities = categoryEntityList)
    }
}