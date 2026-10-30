package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProgresoScreen(
    progreso: Float = 0.6f,
    porcentaje: Int = 60,
    respuestasRegistradas: Int = 0
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement =
            Arrangement.spacedBy(20.dp),
        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Text(
            text = "Mi progreso",
            style =
                MaterialTheme
                    .typography
                    .headlineMedium
        )

        Text(
            text = "Avance general"
        )

        LinearProgressIndicator(
            progress = {
                progreso.coerceIn(
                    0f,
                    1f
                )
            }
        )

        Text(
            text =
                "$porcentaje% completado"
        )

        if (
            respuestasRegistradas > 0
        ) {

            Text(
                text =
                    "Respuestas registradas: " +
                            respuestasRegistradas
            )
        }
    }
}