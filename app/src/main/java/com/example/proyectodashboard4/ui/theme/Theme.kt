package com.example.proyectodashboard4.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
private val DarkColorScheme = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorScheme = lightColors(
    primary = Greens,
    primaryVariant = blackgreen,
    secondary = Teal200

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ProyectoDashboardTheme(
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }


    androidx.compose.material.MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}