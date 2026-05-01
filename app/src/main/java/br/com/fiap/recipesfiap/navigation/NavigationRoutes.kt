package br.com.fiap.recipesfiap.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.recipesfiap.screens.AlertasScreen
import br.com.fiap.recipesfiap.screens.CreditsScreen
import br.com.fiap.recipesfiap.screens.DashboardScreen
import br.com.fiap.recipesfiap.screens.EstoqueScreen
import br.com.fiap.recipesfiap.screens.FunctionalDataScreen
import br.com.fiap.recipesfiap.screens.InstitutionalLoginScreen
import br.com.fiap.recipesfiap.screens.LogisticaScreen
import br.com.fiap.recipesfiap.screens.PedidoScreen
import br.com.fiap.recipesfiap.screens.PrevisaoIAScreen
import br.com.fiap.recipesfiap.screens.SplashScreen

@Composable
fun NavigationRoutes() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.SplashScreen.route
    ) {
        composable(Destination.SplashScreen.route) {
            SplashScreen(navController)
        }

        composable(Destination.LoginScreen.route) {
            InstitutionalLoginScreen(navController)
        }

        composable(
            route = Destination.FunctionalDataScreen.route,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStack ->
            val email = backStack.arguments?.getString("email") ?: ""
            FunctionalDataScreen(navController, email)
        }

        composable(
            route = Destination.DashboardScreen.route,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStack ->
            val email = backStack.arguments?.getString("email") ?: ""
            DashboardScreen(navController, email)
        }

        composable(Destination.EstoqueScreen.route) {
            EstoqueScreen(navController)
        }

        composable(Destination.AlertasScreen.route) {
            AlertasScreen(navController)
        }

        composable(Destination.LogisticaScreen.route) {
            LogisticaScreen(navController)
        }

        composable(Destination.PrevisaoIAScreen.route) {
            PrevisaoIAScreen(navController)
        }

        composable(Destination.PedidoScreen.route) {
            PedidoScreen(navController)
        }

        composable(Destination.CreditsScreen.route) {
            CreditsScreen(navController)
        }
    }
}