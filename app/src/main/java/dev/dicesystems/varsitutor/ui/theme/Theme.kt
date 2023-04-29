package dev.dicesystems.varsitutor.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = BrandBlue,
    secondary = Aurora30,
    tertiary = Aurora40,
    error = Aurora10,

    onPrimary = SnowStorm30,
    onSecondary = Aurora10,
    onTertiary = SnowStorm10,

    primaryContainer = Frost60,
    secondaryContainer = Aurora20,
    tertiaryContainer = Aurora50,

    onPrimaryContainer = SnowStorm10,
    onSecondaryContainer = SnowStorm10,
    onTertiaryContainer = SnowStorm10,

    background = PolarNight80,
    surface = PolarNight60,
)

private val LightColorScheme = lightColorScheme(
    primary = BrandBlue,
    secondary = Aurora30,
    tertiary = Aurora40,

    onPrimary = SnowStorm30,
    onSecondary = Aurora10,
    onTertiary = SnowStorm10,

    primaryContainer = Frost60,
    secondaryContainer = Aurora20,
    tertiaryContainer = Aurora50,

    onPrimaryContainer = SnowStorm10,
    onSecondaryContainer = SnowStorm10,
    onTertiaryContainer = SnowStorm10,

    background = SnowStorm10,
    surface = Color.White,
    surfaceVariant = SnowStorm30,

)

@Composable
fun VarsitutorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )

}