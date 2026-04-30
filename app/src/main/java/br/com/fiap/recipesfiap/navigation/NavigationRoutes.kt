package br.com.fiap.recipesfiap.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import br.com.fiap.recipesfiap.screens.HomeScreen
import br.com.fiap.recipesfiap.screens.InitialScreen
import br.com.fiap.recipesfiap.screens.LoginScreen
import br.com.fiap.recipesfiap.screens.SignUpScreen
import br.com.fiap.recipesfiap.screens.CategoryRecipeScreen

@Composable
fun NavigationRoutes() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.InitialScreen.route
    ){
        composable(Destination.InitialScreen.route){
            InitialScreen(navController)
        }
        composable(
            route = Destination.HomeScreen.route,
            deepLinks = listOf(
                navDeepLink { uriPattern = "https://recipes.fiap.com.br/email/{email}"
                action = Intent.ACTION_VIEW
                }
            ),
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ){ backStackEntry ->
            var email = backStackEntry.arguments?.getString("email")
            HomeScreen(navController, email)
        }
        composable(Destination.SignUpScreen.route) {
            SignUpScreen(navController)
        }
        composable(Destination.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(
            route = Destination.CategoryRecipeScreen.route,
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId")
            CategoryRecipeScreen(categoryId, navController)
        }
    }
}