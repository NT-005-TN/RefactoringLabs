package com.example.refactoringapp.welcomeAfter

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.refactoringapp.theme.WelcomeSubtitleStyle

/**
 * Подзаголовок приветственного экрана с описанием приложения.
 */
@Composable
internal fun WelcomeSubtitle(modifier: Modifier = Modifier) {
    Text(
        text = "Ваши любимые кофейни ждут вас",
        style = WelcomeSubtitleStyle,
        modifier = modifier
    )
}