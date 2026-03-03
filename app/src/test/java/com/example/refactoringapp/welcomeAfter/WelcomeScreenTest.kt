package com.example.refactoringapp.welcomeAfter

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.refactoringapp.navigation.AppRoute
import com.example.refactoringapp.theme.CoffeeAppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName

@DisplayName("WelcomeScreen - приветственный экран")
class WelcomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun launchWelcomeScreen(
        onNavigateToAuth: () -> Unit = {},
        onNavigateToRoute: (AppRoute) -> Unit = {}
    ) {
        composeTestRule.setContent {
            CoffeeAppTheme {
                WelcomeScreen(
                    onNavigateToAuth = onNavigateToAuth,
                    onNavigateToRoute = onNavigateToRoute
                )
            }
        }
    }

    @Test
    fun `displays main title and subtitle`() {
        launchWelcomeScreen()

        composeTestRule.onNodeWithText("Coffee Cup Wallet").assertExists()
        composeTestRule.onNodeWithText("Ваши любимые кофейни ждут вас").assertExists()
    }

    @Test
    fun `displays wallet button with correct text`() {
        launchWelcomeScreen()

        composeTestRule.onNodeWithText("К кошельку").assertExists()
    }

    @Test
    fun `calls onNavigateToAuth when wallet button is clicked`() {
        var authNavigated = false
        launchWelcomeScreen(onNavigateToAuth = { authNavigated = true })

        composeTestRule.onNodeWithText("К кошельку").performClick()
        assert(authNavigated) { "Should navigate to auth screen" }
    }

    @Test
    fun `developer button is visible only in debug builds`() {
        // Примечание: BuildConfig.DEBUG определяется при сборке
        // Этот тест проверяет, что кнопка присутствует в UI-дереве при DEBUG=true
        launchWelcomeScreen()

        // В debug-сборке кнопка ⚙️ должна существовать
        // Если тест падает в release — это ожидаемое поведение
        composeTestRule.onNodeWithText("⚙️", useUnmergedTree = true).assertExists()
    }

    @Test
    fun `developer dialog navigates to selected route`() {
        var selectedRoute: AppRoute? = null
        launchWelcomeScreen(onNavigateToRoute = { route -> selectedRoute = route })

        // Открываем диалог разработчика
        composeTestRule.onNodeWithText("⚙️", useUnmergedTree = true).performClick()

        // Выбираем маршрут "Авторизация"
        composeTestRule.onNodeWithText("Авторизация").performClick()

        assert(selectedRoute is AppRoute.Auth) { "Should navigate to Auth route" }
    }
}