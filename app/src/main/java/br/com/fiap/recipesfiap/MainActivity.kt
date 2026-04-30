package br.com.fiap.recipesfiap

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.fiap.recipesfiap.navigation.NavigationRoutes
import br.com.fiap.recipesfiap.screens.InitialScreen
import br.com.fiap.recipesfiap.screens.LoginScreen
import br.com.fiap.recipesfiap.screens.SignUpScreen
import br.com.fiap.recipesfiap.ui.theme.RecipesFiapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipesFiapTheme {
                NavigationRoutes()
            }
        }
    }
}

