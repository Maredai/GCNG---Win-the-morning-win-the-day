package com.example.ui.theme

import androidx.compose.runtime.compositionLocalOf

val LocalThemeController = compositionLocalOf<ThemeController> {
    error("No ThemeController provided")
}

interface ThemeController {
    val isDarkTheme: Boolean
    fun toggleTheme()
}
