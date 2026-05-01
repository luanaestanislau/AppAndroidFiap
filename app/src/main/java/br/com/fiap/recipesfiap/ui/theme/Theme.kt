package br.com.fiap.recipesfiap.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import br.com.fiap.recipes.ui.theme.Typography

private val MediStockColorScheme = darkColorScheme(
    primary = MediPrimary,
    onPrimary = MediOnPrimary,
    secondary = MediSecondary,
    onSecondary = MediOnSecondary,
    tertiary = MediTertiary,
    onTertiary = MediOnTertiary,
    background = MediBackground,
    onBackground = MediOnBackground,
    surface = MediSurface,
    onSurface = MediOnSurface,
    error = MediError,
    onError = MediOnPrimary
)

@Composable
fun RecipesFiapTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MediStockColorScheme,
        typography = Typography,
        content = content
    )
}
