package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.appmpazpro2.model.ContenidoUiState
import com.example.appmpazpro2.model.EstadoContenido
import com.example.appmpazpro2.ui.components.BarraVolver

@Composable
fun ContenidosObjetivoScreen(
    asignatura: String,
    unidad: String,
    objetivo: String,
    onVolverClick: () -> Unit,
    contenidos: List<ContenidoUiState>,
    onNuevoClick: () -> Unit,
    puedeDeshabilitar: Boolean,
    onReactivarClick: (ContenidoUiState) -> Unit,
    onEliminarClick: (ContenidoUiState) -> Unit,
    onDeshabilitarClick: (ContenidoUiState) -> Unit,
    onEditarClick: (ContenidoUiState) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        BarraVolver(
            titulo = "Contenidos",
            onVolverClick = onVolverClick
        )

        Text(
            text = asignatura,
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = unidad
        )

        Text(
            text = "Objetivo: $objetivo"
        )

        Button(
            onClick = onNuevoClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Nuevo contenido")
        }

        if (contenidos.isEmpty()) {

            Text(
                text = "Aun no hay contenidos para este objetivo.",
                modifier = Modifier.padding(top = 16.dp)
            )

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = contenidos,
                    key = { it.id }
                ) { contenido ->

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            Text(
                                text = contenido.titulo,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = "Tipo: ${contenido.tipo}"
                            )

                            Text(
                                text = contenido.contenido
                            )

                            val textoEstado = when (contenido.estado) {

                                EstadoContenido.PENDIENTE ->
                                    "Pendiente de autorizacion"

                                EstadoContenido.APROBADO ->
                                    "Aprobada"

                                EstadoContenido.RECHAZADO ->
                                    "Rechazada"

                                EstadoContenido.OCULTO ->
                                    "Deshabilitada"
                            }

                            Text(
                                text = "Estado: $textoEstado",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )

                            Button(
                                onClick = {
                                    onEditarClick(contenido)
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Editar")
                            }

                            if (
                                puedeDeshabilitar &&
                                contenido.estado == EstadoContenido.APROBADO
                            ) {

                                Button(
                                    onClick = {
                                        onDeshabilitarClick(contenido)
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Deshabilitar")
                                }
                            }

                            if (
                                puedeDeshabilitar &&
                                contenido.estado == EstadoContenido.OCULTO
                            ) {

                                Button(
                                    onClick = {
                                        onReactivarClick(contenido)
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Reactivar")
                                }
                            }

                            if (puedeDeshabilitar) {

                                Button(
                                    onClick = {
                                        onEliminarClick(contenido)
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}