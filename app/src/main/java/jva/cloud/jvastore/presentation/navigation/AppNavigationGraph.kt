package jva.cloud.jvastore.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import jva.cloud.jvastore.presentation.view.cartview.CartView
import jva.cloud.jvastore.presentation.view.detailstore.DetailStoreView
import jva.cloud.jvastore.presentation.view.storeview.StoreView

@Composable
fun AppNavigationGraph(navController: NavHostController): Unit {

    NavHost(navController = navController, startDestination = RouterPath.STORE) {
        composable(route = RouterPath.STORE) {
            StoreView(navigateToStoreDetail = { id ->
                navController.navigateToStoreDetail(id = id)
            }, navigateToCart = { navController.navigateToCart() })
        }
        composable(
            route = RouterPath.DETAIL_STORE, arguments = listOf(
                navArgument(RouterOption.DetailParam.ID) {
                    requireNotNull(RouterOption.DetailParam.ID)
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString(RouterOption.DetailParam.ID)
            DetailStoreView(id = id, navigateToStore = { navController.popBackStack() })
        }
        composable(route = RouterPath.CART) {
            CartView(navigateToStore = { navController.popBackStack() })
        }
    }
}