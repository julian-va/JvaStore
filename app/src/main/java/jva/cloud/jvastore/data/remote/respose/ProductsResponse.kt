package jva.cloud.jvastore.data.remote.respose

data class ProductsResponse(
    val category: CategoryResponse,
    val creationAt: String,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val title: String,
    val updatedAt: String
)