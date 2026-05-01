package br.com.fiap.recipesfiap.navigation

sealed class Destination(val route: String) {
    object SplashScreen : Destination("splash")
    object LoginScreen : Destination("login")
    object FunctionalDataScreen : Destination("functionalData/{email}") {
        fun createRoute(email: String) = "functionalData/$email"
    }
    object DashboardScreen : Destination("dashboard/{email}") {
        fun createRoute(email: String) = "dashboard/$email"
    }
    object EstoqueScreen : Destination("estoque")
    object AlertasScreen : Destination("alertas")
    object LogisticaScreen : Destination("logistica")
    object PrevisaoIAScreen : Destination("previsaoIA")
    object PedidoScreen : Destination("pedido")
    object CreditsScreen : Destination("credits")

    // Mantidas para compatibilidade com código legado (não usadas no fluxo principal)
    object InitialScreen : Destination("initial")
    object SignupScreen : Destination("signup")
    object ProfileScreen : Destination("profile/{email}") {
        fun createRoute(email: String) = "profile/$email"
    }
    object HomeScreen : Destination("home/{email}") {
        fun createRoute(email: String) = "home/$email"
    }
    object AddRecipeScreen : Destination("addRecipeScreen")
    object CategoryRecipeScreen : Destination("categoryRecipes/{id}") {
        fun createRoute(id: Int) = "categoryRecipes/$id"
    }
    object AddRecipeIngredientsScreen : Destination("addIngredients/{recipeId}/{recipeName}") {
        fun createRoute(recipeId: Int, recipeName: String) = "addIngredients/$recipeId/$recipeName"
    }
    object AddPreparationMethodsScreen : Destination("addPreparationMethods/{recipeId}/{recipeName}") {
        fun createRoute(recipeId: Int, recipeName: String) = "addPreparationMethods/$recipeId/$recipeName"
    }
    object AddRecipePhoto : Destination("addRecipePhoto/{recipeId}") {
        fun createRoute(recipeId: Int) = "addRecipePhoto/$recipeId"
    }
}