package com.example.refactoringapp.welcomeAfter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.refactoringapp.R

/**
 * Фоновое изображение для приветственного экрана.
 * Заполняет весь экран с масштабированием FillBounds.
 */
@Composable
internal fun BackgroundImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.coffee_cup),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier.fillMaxSize()
    )
}