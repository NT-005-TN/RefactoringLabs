package com.example.refactoringapp.welcomeAfter

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.refactoringapp.theme.CoffeeDarkBrown
import com.example.refactoringapp.theme.WalletButtonStyle

/**
 * Основная кнопка перехода к кошельку.
 * @param onClick обработчик нажатия
 * @param modifier модификатор для кастомизации
 */
@Composable
internal fun WalletButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = CoffeeDarkBrown
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp)
    ) {
        Text(
            text = "К кошельку",
            style = WalletButtonStyle
        )
    }
}