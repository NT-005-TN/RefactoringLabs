package com.example.refactoringapp.welcomeAfter

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.refactoringapp.navigation.AppRoute

/**
 * Кнопка разработчика для отладки навигации.
 * Отображается только в debug-сборках.
 *
 * @param onNavigate колбэк для перехода по маршруту
 * @param modifier модификатор для позиционирования
 */
@Composable
internal fun DeveloperDebugButton(
    onNavigate: (AppRoute) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }

    TextButton(
        onClick = { showDialog = true },
        modifier = modifier.size(40.dp)
    ) {
        Text(
            text = "⚙️",
            fontSize = 20.sp
        )
    }

    if (showDialog) {
        DeveloperNavigationDialog(
            onDismiss = { showDialog = false },
            onNavigate = { route ->
                showDialog = false
                onNavigate(route)
            }
        )
    }
}