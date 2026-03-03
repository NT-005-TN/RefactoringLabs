package com.example.refactoringapp.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.refactoringapp.theme.CoffeeAppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName

@DisplayName("BoxWithBackButton - компонент с кнопкой назад")
class BoxWithBackButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `displays title and back button`() {
        composeTestRule.setContent {
            CoffeeAppTheme {
                BoxWithBackButton(
                    title = "Тестовый экран",
                    onBack = {}
                ) {
                    // пустой контент
                }
            }
        }

        composeTestRule.onNodeWithText("← Назад").assertExists()
        composeTestRule.onNodeWithText("Тестовый экран").assertExists()
    }

    @Test
    fun `calls onBack when back button is clicked`() {
        var backClicked = false
        composeTestRule.setContent {
            CoffeeAppTheme {
                BoxWithBackButton(
                    title = "Test",
                    onBack = { backClicked = true }
                ) {}
            }
        }

        composeTestRule.onNodeWithText("← Назад").performClick()
        assert(backClicked) { "onBack callback should be invoked" }
    }

    @Test
    fun `renders custom content inside container`() {
        composeTestRule.setContent {
            CoffeeAppTheme {
                BoxWithBackButton(title = "Test", onBack = {}) {
                    androidx.compose.material3.Text(text = "Custom Content")
                }
            }
        }

        composeTestRule.onNodeWithText("Custom Content").assertExists()
    }
}