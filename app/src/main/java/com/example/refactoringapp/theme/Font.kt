package com.example.refactoringapp.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.refactoringapp.R

/**
 * Конфигурация для Google Fonts
 */
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

/**
 * Настройки шрифта Josefin Sans
 */
val josefinSansFont = GoogleFont("Josefin Sans")

/**
 * Семейство шрифтов Josefin Sans с различными начертаниями
 */
val JosefinSansFontFamilyBold = FontFamily(
    Font(
        googleFont = josefinSansFont,
        fontProvider = provider,
        weight = FontWeight.Bold
    )
)

/**
 * Настройки шрифта Poppins
 */
val poppinsFont = GoogleFont("Poppins")

/**
 * Семейство шрифтов Poppins с различными начертаниями
 */
val Poppins = FontFamily(
    Font(
        googleFont = poppinsFont,
        fontProvider = provider,
        weight = FontWeight.Normal
    ),
    Font(
        googleFont = poppinsFont,
        fontProvider = provider,
        weight = FontWeight.Medium
    ),
    Font(
        googleFont = poppinsFont,
        fontProvider = provider,
        weight = FontWeight.SemiBold
    ),
    Font(
        googleFont = poppinsFont,
        fontProvider = provider,
        weight = FontWeight.Bold
    )
)