package com.example.refactoringapp.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = CoffeeDarkBrown,
    onPrimary = CoffeeLightBeige,
    background = CoffeeBackground,
    onBackground = CoffeeDarkBrown,
    surface = CoffeeWhite,
    onSurface = CoffeeDarkBrown,
    error = CoffeeError,
    onError = CoffeeWhite
)

private val DarkColorScheme = darkColorScheme(
    primary = CoffeeLightBeige,
    onPrimary = CoffeeDarkBrown,
    background = CoffeeDarkBrown,
    onBackground = CoffeeLightBeige,
    surface = Color(0xFF1A0F0A),
    onSurface = CoffeeLightBeige,
    error = CoffeeError,
    onError = CoffeeWhite
)

@Composable
fun CoffeeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) {
                darkColorScheme().copy(primary = CoffeeDarkBrown, secondary = CoffeeLightBeige)
            } else {
                lightColorScheme().copy(primary = CoffeeDarkBrown, secondary = CoffeeLightBeige)
            }
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}