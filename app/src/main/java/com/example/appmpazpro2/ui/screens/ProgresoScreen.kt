package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProgresoScreen(
    progreso: Float = 0f,
    porcentaje: Int = 0,
    respuestasRegistradas: Int = 0,
    respuestasCorrectas: Int = 0,
    respuestasIncorrectas: Int = 0,
    respuestasPendientes: Int = 0
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Mi progreso", style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Avance: $porcentaje%", style = MaterialTheme.typography.titleLarge
        )

        LinearProgressIndicator(
            progress = {
                progreso.coerceIn(
                    0f, 1f
                )
            }, modifier = Modifier.fillMaxWidth()
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Text(
                    text = "Respuestas realizadas: $respuestasRegistradas"
                )

                Text(
                    text = "Correctas: $respuestasCorrectas"
                )

                Text(
                    text = "Incorrectas: $respuestasIncorrectas"
                )

                Text(
                    text = "Pendientes de revision: $respuestasPendientes"
                )
            }
        }
    }
}