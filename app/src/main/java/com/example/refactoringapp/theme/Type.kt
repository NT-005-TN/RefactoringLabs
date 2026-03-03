package com.example.refactoringapp.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val Typography = Typography(
    headlineLarge = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = JosefinSansFontFamilyBold,
        color = CoffeeDarkBrown
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = Poppins,
        color = CoffeeDarkBrown
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = Poppins,
        color = CoffeeDarkBrown.copy(alpha = 0.8f)
    )
)

val WelcomeTitleStyle = TextStyle(
    fontSize = 36.sp,
    fontWeight = FontWeight.Bold,
    fontFamily = JosefinSansFontFamilyBold,
    color = CoffeeDarkBrown
)

val WelcomeSubtitleStyle = TextStyle(
    fontSize = 22.sp,
    fontFamily = Poppins,
    color = CoffeeDarkBrown,
    textAlign = TextAlign.Center
)

val WalletButtonStyle = TextStyle(
    fontSize = 18.sp,
    fontFamily = Poppins,
    fontWeight = FontWeight.Medium,
    color = CoffeeLightBeige
)

val DialogTextStyle = TextStyle(
    fontSize = 16.sp,
    fontFamily = Poppins,
    color = CoffeeDarkBrown
)