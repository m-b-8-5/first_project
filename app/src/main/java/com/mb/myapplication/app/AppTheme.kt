package com.mb.myapplication.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val darkColorScheme = darkColorScheme(
    primary = Color.LightGray,
    secondary = Color.Black,
    tertiary = Color.Transparent
)

private val lightColorScheme = lightColorScheme(
    primary = Color.LightGray,
    secondary = Color.Black,
    tertiary = Color.Transparent
)
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val appColor = if (darkTheme) darkColorScheme else lightColorScheme
    MaterialTheme(
        colorScheme = appColor,
        typography = Typography,
        content = content
    )
}