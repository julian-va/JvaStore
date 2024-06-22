package jva.cloud.jvastore.data.remote.repository

import jva.cloud.jvastore.data.remote.respose.ProductsResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsRepository {
    @GET(value = "products")
    suspend fun getAllProducts(): Response<List<ProductsResponse>>

    @GET(value = "products/{id}")
    suspend fun getByProducts(@Path("id") id: Int): Response<ProductsResponse>

    @DELETE(value = "products/{id}")
    suspend fun deleteProduct(@Path("id") id: Int): Response<Boolean>
}