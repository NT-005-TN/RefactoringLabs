package com.example.refactoringapp.welcomeAfter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Константы для устранения магических чисел
private val TopWeight = 5f
private val BottomWeight = 0.8f
private val HorizontalPadding = 16.dp
private val ButtonVerticalPadding = 64.dp
private val TitleVerticalPadding = 8.dp
private val SubtitleHorizontalPadding = 32.dp
private val SubtitleVerticalPadding = 20.dp

/**
 * Основной контент приветственного экрана:
 * заголовок, подзаголовок и кнопка действия.
 *
 * @param onNavigateToAuth обработчик перехода к авторизации
 * @param modifier модификатор для позиционирования
 */
@Composable
internal fun WelcomeContent(
    onNavigateToAuth: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(HorizontalPadding),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(TopWeight))

        WelcomeTitle(
            modifier = Modifier.padding(vertical = TitleVerticalPadding)
        )

        WelcomeSubtitle(
            modifier = Modifier
                .padding(horizontal = SubtitleHorizontalPadding)
                .padding(vertical = SubtitleVerticalPadding)
        )

        WalletButton(
            onClick = onNavigateToAuth,
            modifier = Modifier.padding(vertical = ButtonVerticalPadding)
        )

        Spacer(modifier = Modifier.weight(BottomWeight))
    }
}