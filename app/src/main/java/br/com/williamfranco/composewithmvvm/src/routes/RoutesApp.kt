package br.com.williamfranco.composewithmvvm.src.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.williamfranco.composewithmvvm.src.features.settings.routes.SettingRoute
import br.com.williamfranco.composewithmvvm.src.features.users.routes.UserDetailRoute
import br.com.williamfranco.composewithmvvm.src.features.users.routes.UserRoute

@Composable
fun RoutesApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.USERS,
    ) {
        composable(Routes.USERS) {
            UserRoute(
                onOpenSettings = { navController.navigate(Routes.SETTINGS) },
                onUserClick = { user ->
                    user.id?.let { navController.navigate(Routes.userDetail(it)) }
                },
            )
        }

        composable(
            route = Routes.USER_DETAIL,
            arguments = listOf(
                navArgument(Routes.ARG_USER_ID) { type = NavType.IntType },
            ),
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt(Routes.ARG_USER_ID) ?: return@composable
            UserDetailRoute(
                userId = userId,
                onBack = navController::popBackStack,
            )
        }

        composable(Routes.SETTINGS) {
            SettingRoute(onBack = navController::popBackStack)
        }
    }
}
