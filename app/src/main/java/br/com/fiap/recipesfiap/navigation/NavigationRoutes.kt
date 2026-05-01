package br.com.fiap.recipesfiap.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.recipesfiap.screens.AlertasScreen
import br.com.fiap.recipesfiap.screens.ConfirmDataScreen
import br.com.fiap.recipesfiap.screens.CreditsScreen
import br.com.fiap.recipesfiap.screens.DashboardScreen
import br.com.fiap.recipesfiap.screens.EstoqueScreen
import br.com.fiap.recipesfiap.screens.IAScreen
import br.com.fiap.recipesfiap.screens.InstitutionalLoginScreen
import br.com.fiap.recipesfiap.screens.LogisticaScreen
import br.com.fiap.recipesfiap.screens.PedidoScreen
import br.com.fiap.recipesfiap.screens.SplashScreen

@Composable
fun NavigationRoutes() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.Splash.route
    ) {
        composable(Destination.Splash.route) {
            SplashScreen(navController)
        }
        composable(Destination.Login.route) {
            InstitutionalLoginScreen(navController)
        }
        composable(Destination.ConfirmData.route) {
            ConfirmDataScreen(navController)
        }
        composable(Destination.Dashboard.route) {
            DashboardScreen(navController, currentTab = "home")
        }
        composable(Destination.Estoque.route) {
            DashboardScreen(navController, currentTab = "estoque")
        }
        composable(Destination.Alertas.route) {
            DashboardScreen(navController, currentTab = "alertas")
        }
        composable(Destination.IA.route) {
            DashboardScreen(navController, currentTab = "ia")
        }
        composable(Destination.Logistica.route) {
            DashboardScreen(navController, currentTab = "logistica")
        }
        composable(Destination.Pedido.route) {
            DashboardScreen(navController, currentTab = "pedido")
        }
        composable(Destination.Credits.route) {
            CreditsScreen(navController)
        }
    }
}
