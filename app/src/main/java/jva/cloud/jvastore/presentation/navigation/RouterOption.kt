package jva.cloud.jvastore.presentation.navigation

object RouterOption {
    object DetailParam {
        const val ID: String = "id"
        const val BASE_ROUTE = "/store/detail"
        const val FULL_ROUTE = "$BASE_ROUTE/{$ID}"
    }
}