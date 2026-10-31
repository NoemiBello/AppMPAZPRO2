package com.example.appmpazpro2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = AzulClaroMPaz,
    secondary = MoradoClaroMPaz,
    tertiary = VerdeClaroMPaz
)

private val LightColorScheme = lightColorScheme(
    primary = AzulMPaz,
    onPrimary = SuperficieMPaz,

    secondary = MoradoMPaz,
    onSecondary = SuperficieMPaz,

    tertiary = VerdeMPaz,

    background = FondoMPaz,
    onBackground = TextoMPaz,

    surface = SuperficieMPaz,
    onSurface = TextoMPaz,

    primaryContainer = AzulClaroMPaz,
    secondaryContainer = MoradoClaroMPaz,
    tertiaryContainer = VerdeClaroMPaz
)

@Composable
fun AppMPAZPRO2Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}