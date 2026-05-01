package br.com.fiap.recipesfiap.navigation

sealed class Destination(val route: String) {
    object Splash : Destination("splash")
    object Login : Destination("login")
    object ConfirmData : Destination("confirm_data")
    object Dashboard : Destination("dashboard")
    object Estoque : Destination("estoque")
    object Alertas : Destination("alertas")
    object IA : Destination("ia")
    object Logistica : Destination("logistica")
    object Pedido : Destination("pedido")
    object Credits : Destination("credits")
}
