package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appmpazpro2.model.ContenidoUiState
import com.example.appmpazpro2.model.EstadoRespuesta
import com.example.appmpazpro2.model.RespuestaUiState

@Composable
fun RevisionRespuestasScreen(
    respuestas: List<RespuestaUiState>,
    contenidos: List<ContenidoUiState>,
    onCorrectaClick: (Int) -> Unit,
    onIncorrectaClick: (Int) -> Unit
) {

    val respuestasOrdenadas = respuestas
        .groupBy { it.contenidoId }
        .mapNotNull { (_, intentos) ->
            intentos.maxByOrNull { it.id }
        }
        .sortedByDescending { it.id }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Revision de respuestas", style = MaterialTheme.typography.headlineMedium
        )

        if (respuestasOrdenadas.isEmpty()) {

            Text(
                text = "No hay respuestas registradas"
            )

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = respuestasOrdenadas,
                    key = { it.id }
                ) { respuesta ->

                    val intentosUsados = respuestas.count {
                        it.contenidoId == respuesta.contenidoId
                    }
                    val contenido = contenidos.firstOrNull {
                        it.id == respuesta.contenidoId
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            Text(
                                text = contenido?.titulo ?: "Contenido",
                                style = MaterialTheme.typography.titleLarge
                            )

                            Text(
                                text = "Asignatura: ${
                                    contenido?.asignatura ?: "Sin informacion"
                                }"
                            )

                            Text(
                                text = "Unidad: ${
                                    contenido?.unidad ?: "Sin informacion"
                                }"
                            )

                            Text(
                                text = "Objetivo: ${
                                    contenido?.objetivo ?: "Sin informacion"
                                }"
                            )

                            Text(
                                text = "Respuesta del estudiante:",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = respuesta.respuesta,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = when (respuesta.estado) {
                                    EstadoRespuesta.PENDIENTE_REVISION ->
                                        "Estado: Pendiente de revisión"

                                    EstadoRespuesta.CORRECTA ->
                                        "Estado: Correcta"

                                    EstadoRespuesta.INCORRECTA ->
                                        "Estado: Incorrecta"
                                },
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Intentos: $intentosUsados de 3",
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = {
                                        onCorrectaClick(respuesta.id)
                                    }
                                ) {
                                    Text("Correcta")
                                }

                                Button(
                                    onClick = {
                                        onIncorrectaClick(respuesta.id)
                                    }
                                ) {
                                    Text("Incorrecta")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}