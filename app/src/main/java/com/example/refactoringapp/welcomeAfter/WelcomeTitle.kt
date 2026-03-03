package com.example.refactoringapp.welcomeAfter

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.refactoringapp.theme.WelcomeTitleStyle

/**
 * Заголовок приветственного экрана "Coffee Cup Wallet".
 */
@Composable
internal fun WelcomeTitle(modifier: Modifier = Modifier) {
    Text(
        text = "Coffee Cup Wallet",
        style = WelcomeTitleStyle,
        modifier = modifier
    )
}