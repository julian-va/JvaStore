package jva.cloud.jvastore.presentation.navigation

import androidx.navigation.NavController

fun NavController.navigateToStore(): Unit {
    this.navigate(route = RouterPath.STORE)
}

fun NavController.navigateToStoreDetail(id: String): Unit {
    this.navigate(route = "${RouterOption.DetailParam.BASE_ROUTE}/$id")
}