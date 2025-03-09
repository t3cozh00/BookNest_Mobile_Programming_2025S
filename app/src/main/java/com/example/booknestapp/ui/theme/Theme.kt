package com.example.booknestapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// 🌙 Dark Theme Colors
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFa52a2a),
    secondary = Color(0xFF79443b), // for subtitle
    background = Color(0xFF121212), // Dark background
    surface = Color(0xFF1E1E1E), // Slightly lighter than background for cards
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White, // White text for readability
    onSurface = Color.LightGray, // Lighter gray for subtitles
    error = Color(0xFFFF6B6B) // Soft Red for errors
)
/*
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)
*/

// ☀️ Light Theme Colors
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFa52a2a),
    secondary = Color(0xFF79443b),
    background = Color(0xFFf0ffff),
    surface = Color(0xFFF5F5F5), // Light Gray for subtle contrast
    onPrimary = Color.White, // Scaffold.containerColor, TopAppBar.containerColor
    onSecondary = Color.Black, // Black text for top bar, app name
    onBackground = Color.Black, // Black text for readability
    onSurface = Color.DarkGray, // Dark gray for subtitles
    error = Color(0xFFD32F2F) // Dark Red for errors
)
/*
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)
*/


@Composable
fun BookNestAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}