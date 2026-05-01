package br.com.fiap.recipesfiap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.fiap.recipesfiap.navigation.NavigationRoutes
import br.com.fiap.recipesfiap.ui.theme.RecipesFiapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipesFiapTheme {
                NavigationRoutes()
            }
        }
    }
}
