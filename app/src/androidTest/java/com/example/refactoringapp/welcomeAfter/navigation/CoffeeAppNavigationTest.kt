package com.example.refactoringapp.welcomeAfter.navigation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.refactoringapp.MainActivity
import org.junit.Rule
import org.junit.Test

// ✅ Аннотации JUnit 4
class CoffeeAppNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun app_starts_with_welcome_screen() {
        composeTestRule.onNodeWithText("Coffee Cup Wallet").assertExists()
        composeTestRule.onNodeWithText("К кошельку").assertExists()
    }

    @Test
    fun navigates_from_welcome_to_auth_screen() {
        composeTestRule.onNodeWithText("К кошельку").performClick()
        composeTestRule.onNodeWithText("Авторизация").assertExists()
        composeTestRule.onNodeWithText("← Назад").assertExists()
    }

    @Test
    fun back_button_returns_to_previous_screen() {
        composeTestRule.onNodeWithText("К кошельку").performClick()
        composeTestRule.onNodeWithText("← Назад").performClick()
        composeTestRule.onNodeWithText("Coffee Cup Wallet").assertExists()
    }

    @Test
    fun navigates_to_qr_code_screen_with_parameter() {
        // Открываем dev-меню и выбираем маршрут
        composeTestRule.onNodeWithText("⚙️", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithText("QR-код (cardId=1)").performClick()
        composeTestRule.onNodeWithText("QR-код карты #1").assertExists()
    }
}