package jva.cloud.jvastore.domain.usecase.impl

import jva.cloud.jvastore.data.remote.repository.ProductsRepository
import jva.cloud.jvastore.domain.model.Product
import jva.cloud.jvastore.domain.model.productsResponseToProduct
import jva.cloud.jvastore.domain.usecase.RetrieveProductById
import javax.inject.Inject

class RetrieveProductByIdImpl @Inject constructor(private val productsRepository: ProductsRepository) :
    RetrieveProductById {
    override suspend fun getProductById(id: String): Product? {
        var product: Product? = null
        try {
            val response = productsRepository.getByProducts(id = id.toInt())
            if (response.isSuccessful) {
                product = response.body()
                    ?.let { productsResponses ->
                        productsResponseToProduct(response = productsResponses)
                    }
            }

        } catch (ex: Exception) {
            println("failed to users recovered to the server, the error is: ${ex.message}")
        }
        return product
    }
}