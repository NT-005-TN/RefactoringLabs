package com.example.refactoringapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.refactoringapp.theme.CoffeeBackground
import com.example.refactoringapp.theme.CoffeeDarkBrown
import com.example.refactoringapp.theme.Poppins
import com.example.refactoringapp.theme.WelcomeTitleStyle

/**
 * Универсальный компонент для экранов с кнопкой "Назад".
 * Содержит заголовок, кнопку возврата и область для пользовательского контента.
 *
 * @param title заголовок экрана
 * @param onBack обработчик нажатия кнопки "Назад"
 * @param content пользовательский контент, отображаемый под заголовком
 */
@Composable
fun BoxWithBackButton(
    title: String,
    onBack: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CoffeeBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Кнопка назад
            TextButton(onClick = onBack) {
                Text(
                    text = "← Назад",
                    fontFamily = Poppins,
                    color = CoffeeDarkBrown
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Заголовок экрана
            Text(
                text = title,
                style = WelcomeTitleStyle
            )

            // Пользовательский контент
            content()

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}