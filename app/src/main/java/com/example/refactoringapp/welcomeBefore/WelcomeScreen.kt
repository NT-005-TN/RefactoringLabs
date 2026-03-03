package ru.anasttruh.coffeeapp.reafctoring.welcomeBefore

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.refactoringapp.R
import com.example.refactoringapp.theme.JosefinSansFontFamilyBold
import com.example.refactoringapp.theme.Poppins


@Composable
fun WelcomeScreen(
    onNavigateToAuth: () -> Unit,
    onNavigateToScreen: (String) -> Unit = {} // Новый параметр для навигации разработчика
) {
    var showDevDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.coffee_cup),
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
//
//        TextButton(
//            onClick = { showDevDialog = true },
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(8.dp)
//                .size(40.dp)
//        ) {
//            Text(
//                text = "⚙️",
//                fontSize = 20.sp
//            )
//        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .weight(2.8f)
            )

            Text(
                text = "Coffee Cup Wallet",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = JosefinSansFontFamilyBold,
                color = Color(0xFF230C02),
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
            Text(
                text = "Ваши любимые кофейни ждут вас",
                textAlign = TextAlign.Center,
                color = Color(0xFF230C02),
                fontSize = 22.sp,
                fontFamily = Poppins,
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .padding(vertical = 20.dp)
            )
            Button(
                onClick = onNavigateToAuth,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF230C02)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 64.dp)
            ) {
                Text(
                    text = "К кошельку",
                    color = Color(0xFFEEDDC9),
                    fontFamily = Poppins
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(0.8f)
            )
        }

        // 🛠️ Диалог разработчика
        if (showDevDialog) {
            DeveloperNavigationDialog(
                onDismiss = { showDevDialog = false },
                onNavigate = { route ->
                    showDevDialog = false
                    onNavigateToScreen(route)
                }
            )
        }
    }
}

@Composable
private fun DeveloperNavigationDialog(
    onDismiss: () -> Unit,
    onNavigate: (String) -> Unit
) {
    val devRoutes = listOf(
        "auth" to "Авторизация",
        "reg" to "Регистрация",
        "meet" to "Onboarding",
        "coffeeList" to "Список карт",
        "coffeeActions" to "Акции",
        "profile" to "Профиль",
        "chooseCard" to "Выбор карты",
        "qrCode/1" to "QR-код (cardId=1)",
        "actionProfile/1" to "Детали акции (id=1)"
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "🔧 Dev Navigation",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                devRoutes.forEach { (route, label) ->
                    TextButton(
                        onClick = { onNavigate(route) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = label,
                            color = Color(0xFF230C02),
                            fontFamily = Poppins,
                            textAlign = TextAlign.Start
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
                    color = Color(0xFF230C02)
                )
            }
        },
        containerColor = Color(0xFFEEDDC9)
    )
}

// region Previews

@Preview(showBackground = true, name = "Welcome Screen - Light")
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(
        onNavigateToAuth = { /* No-op for preview */ },
        onNavigateToScreen = { /* No-op for preview */ }
    )
}

@Preview(showBackground = true, name = "Welcome Screen - Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WelcomeScreenDarkPreview() {
    WelcomeScreen(
        onNavigateToAuth = { /* No-op for preview */ },
        onNavigateToScreen = { /* No-op for preview */ }
    )
}

@Preview(device = "spec:width=411dp,height=891dp", name = "Welcome Screen - Pixel 5")
@Composable
private fun WelcomeScreenDevicePreview() {
    WelcomeScreen(
        onNavigateToAuth = { /* No-op for preview */ },
        onNavigateToScreen = { /* No-op for preview */ }
    )
}

// endregion