package br.com.fiap.recipesfiap.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import br.com.fiap.recipes.ui.theme.Typography

private val MediDarkColorScheme = darkColorScheme(
    primary = MediPrimary,
    secondary = MediSecondary,
    background = MediBackground,
    surface = MediSurface,
    error = MediError,
    onPrimary = MediOnBackground,
    onSecondary = MediOnBackground,
    onBackground = MediOnBackground,
    onSurface = MediOnSurface
)

@Composable
fun RecipesFiapTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MediDarkColorScheme,
        typography = Typography,
        content = content
    )
}
