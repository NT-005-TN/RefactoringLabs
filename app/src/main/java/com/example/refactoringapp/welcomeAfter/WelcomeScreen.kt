package com.example.refactoringapp.welcomeAfter

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.refactoringapp.BuildConfig
import com.example.refactoringapp.navigation.AppRoute
import com.example.refactoringapp.theme.CoffeeAppTheme

/**
 * Приветственный экран приложения Coffee Cup Wallet.
 *
 * @param onNavigateToAuth обработчик перехода к экрану авторизации
 * @param onNavigateToRoute обработчик навигации по типобезопасным маршрутам
 */
@Composable
fun WelcomeScreen(
    onNavigateToAuth: () -> Unit,
    onNavigateToRoute: (AppRoute) -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Фон
        BackgroundImage()

        // Основной контент
        WelcomeContent(
            onNavigateToAuth = onNavigateToAuth,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        // Кнопка разработчика (только в debug-сборках)
        if (BuildConfig.DEBUG) {
            DeveloperDebugButton(
                onNavigate = onNavigateToRoute,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

// Previews
private val noopAuth: () -> Unit = {}
private val noopNav: (AppRoute) -> Unit = {}

@Preview(showBackground = true, name = "Welcome Screen - Light")
@Composable
private fun WelcomeScreenPreview() {
    CoffeeAppTheme {
        WelcomeScreen(
            onNavigateToAuth = noopAuth,
            onNavigateToRoute = noopNav
        )
    }
}

@Preview(
    showBackground = true,
    name = "Welcome Screen - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun WelcomeScreenDarkPreview() {
    CoffeeAppTheme {
        WelcomeScreen(
            onNavigateToAuth = noopAuth,
            onNavigateToRoute = noopNav
        )
    }
}

@Preview(
    device = "spec:width=411dp,height=891dp",
    name = "Welcome Screen - Pixel 5"
)
@Composable
private fun WelcomeScreenDevicePreview() {
    CoffeeAppTheme {
        WelcomeScreen(
            onNavigateToAuth = noopAuth,
            onNavigateToRoute = noopNav
        )
    }
}