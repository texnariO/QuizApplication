package com.example.quizapplication.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LightColorPalette = lightColorScheme(
    background = Color(0xFFf4f1ed),
    primary = Color(0xFF000000),
    secondary = Color(0xFF57857c),
    tertiary = Color(0XFF4D504F),
    error = Color(0xFFF09186),
    onError = Color(0xFFE41C05)

)

val DarkColorPalette = darkColorScheme(
    background = Color(0xFF3A3737),
    primary = Color(0xFFF4F1ED),
    secondary = Color(0xFF27523F),
    tertiary = Color(0XFF4D504F),
    error = Color(0xFFEE5E4E),
    onError = Color(0xFFE41C05)
)