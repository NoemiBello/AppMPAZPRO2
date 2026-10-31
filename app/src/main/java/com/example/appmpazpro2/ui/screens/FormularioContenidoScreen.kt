package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appmpazpro2.model.ContenidoErrores
import com.example.appmpazpro2.model.ContenidoUiState

@Composable
fun FormularioContenidoScreen(
    uiState: ContenidoUiState,
    errores: ContenidoErrores,
    onAsignaturaChange: (String) -> Unit,
    onUnidadChange: (String) -> Unit,
    onObjetivoChange: (String) -> Unit,
    onTipoChange: (String) -> Unit,
    onTituloChange: (String) -> Unit,
    onContenidoChange: (String) -> Unit,
    onGuardarClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = "Nuevo contenido",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "2° Básico",
            style = MaterialTheme.typography.titleMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                Text(
                    text = "Información académica",
                    style = MaterialTheme.typography.titleMedium
                )

                OutlinedTextField(
                    value = uiState.asignatura,
                    onValueChange = onAsignaturaChange,
                    label = {
                        Text("Asignatura")
                    },
                    singleLine = true,
                    isError = errores.asignatura != null,
                    trailingIcon = {
                        if (errores.asignatura != null) {
                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = "Error"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                if (errores.asignatura != null) {
                    Text(
                        text = errores.asignatura,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                OutlinedTextField(
                    value = uiState.unidad,
                    onValueChange = onUnidadChange,
                    label = {
                        Text("Unidad")
                    },
                    singleLine = true,
                    isError = errores.unidad != null,
                    trailingIcon = {
                        if (errores.unidad != null) {
                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = "Error"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                if (errores.unidad != null) {
                    Text(
                        text = errores.unidad,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                OutlinedTextField(
                    value = uiState.objetivo,
                    onValueChange = onObjetivoChange,
                    label = {
                        Text("Objetivo de aprendizaje")
                    },
                    singleLine = true,
                    isError = errores.objetivo != null,
                    trailingIcon = {
                        if (errores.objetivo != null) {
                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = "Error"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                if (errores.objetivo != null) {
                    Text(
                        text = errores.objetivo,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                Text(
                    text = "Información del contenido",
                    style = MaterialTheme.typography.titleMedium
                )

                OutlinedTextField(
                    value = uiState.tipo,
                    onValueChange = onTipoChange,
                    label = {
                        Text("Tipo de contenido")
                    },
                    singleLine = true,
                    isError = errores.tipo != null,
                    trailingIcon = {
                        if (errores.tipo != null) {
                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = "Error"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                if (errores.tipo != null) {
                    Text(
                        text = errores.tipo,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                OutlinedTextField(
                    value = uiState.titulo,
                    onValueChange = onTituloChange,
                    label = {
                        Text("Título")
                    },
                    singleLine = true,
                    isError = errores.titulo != null,
                    trailingIcon = {
                        if (errores.titulo != null) {
                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = "Error"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                if (errores.titulo != null) {
                    Text(
                        text = errores.titulo,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                OutlinedTextField(
                    value = uiState.contenido,
                    onValueChange = onContenidoChange,
                    label = {
                        Text("Contenido")
                    },
                    minLines = 2,
                    isError = errores.contenido != null,
                    trailingIcon = {
                        if (errores.contenido != null) {
                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = "Error"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                if (errores.contenido != null) {
                    Text(
                        text = errores.contenido,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        Button(
            onClick = onGuardarClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar contenido")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormularioContenidoScreenPreview() {

    FormularioContenidoScreen(
        uiState = ContenidoUiState(
            asignatura = "Matemática",
            unidad = "Unidad 1",
            objetivo = "Resolver problemas utilizando números y operaciones.",
            tipo = "Actividad",
            titulo = "Ejercicios de números",
            contenido = "Resuelve los siguientes ejercicios."
        ),
        errores = ContenidoErrores(),
        onAsignaturaChange = {},
        onUnidadChange = {},
        onObjetivoChange = {},
        onTipoChange = {},
        onTituloChange = {},
        onContenidoChange = {},
        onGuardarClick = {}
    )
}