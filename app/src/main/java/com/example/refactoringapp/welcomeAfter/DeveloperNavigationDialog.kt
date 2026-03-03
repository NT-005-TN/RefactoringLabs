package com.example.refactoringapp.welcomeAfter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.refactoringapp.navigation.AppRoute
import com.example.refactoringapp.theme.CoffeeDarkBrown
import com.example.refactoringapp.theme.CoffeeLightBeige
import com.example.refactoringapp.theme.DialogTextStyle
import com.example.refactoringapp.theme.Poppins

/**
 * Диалоговое окно с списком маршрутов для отладки.
 *
 * @param onDismiss обработчик закрытия диалога
 * @param onNavigate обработчик выбора маршрута
 */
@Composable
internal fun DeveloperNavigationDialog(
    onDismiss: () -> Unit,
    onNavigate: (AppRoute) -> Unit
) {
    val debugRoutes = AppRoute.getDebugRoutes()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "🔧 Dev Navigation",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                color = CoffeeDarkBrown
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                debugRoutes.forEach { (route, label) ->
                    TextButton(
                        onClick = { onNavigate(route) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = label,
                            style = DialogTextStyle
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Отмена",
                    fontFamily = Poppins,
                    color = CoffeeDarkBrown
                )
            }
        },
        containerColor = CoffeeLightBeige
    )
}