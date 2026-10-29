package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appmpazpro2.model.ContenidoErrores
import com.example.appmpazpro2.model.ContenidoUiState
import androidx.compose.material3.MaterialTheme

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
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Nuevo contenido",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = uiState.asignatura,
            onValueChange = onAsignaturaChange,
            label = { Text("Asignatura") },
            isError = errores.asignatura != null,
            trailingIcon = {
                if (errores.asignatura != null) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error"
                    )
                }
            },
            supportingText = {
                errores.asignatura?.let { Text(it) }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.unidad,
            onValueChange = onUnidadChange,
            label = { Text("Unidad") },
            isError = errores.unidad != null,
            trailingIcon = {
                if (errores.unidad != null) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error"
                    )
                }
            },
            supportingText = {
                errores.unidad?.let { Text(it) }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.objetivo,
            onValueChange = onObjetivoChange,
            label = { Text("Objetivo de aprendizaje") },
            isError = errores.objetivo != null,
            trailingIcon = {
                if (errores.objetivo != null) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error"
                    )
                }
            },
            supportingText = {
                errores.objetivo?.let { Text(it) }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.tipo,
            onValueChange = onTipoChange,
            label = { Text("Tipo de contenido") },
            isError = errores.tipo != null,
            trailingIcon = {
                if (errores.tipo != null) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error"
                    )
                }
            },
            supportingText = {
                errores.tipo?.let { Text(it) }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.titulo,
            onValueChange = onTituloChange,
            label = {
                Text("Título")
            },
            isError = errores.titulo != null,
            trailingIcon = {
                if (errores.titulo != null) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error"
                    )
                }
            },
            supportingText = {
                errores.titulo?.let {
                    Text(it)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.contenido,
            onValueChange = onContenidoChange,
            label = {
                Text("Contenido")
            },
            isError = errores.contenido != null,
            trailingIcon = {
                if (errores.contenido != null) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error"
                    )
                }
            },
            supportingText = {
                errores.contenido?.let {
                    Text(it)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onGuardarClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormularioContenidoScreenPreview() {
    FormularioContenidoScreen(
        uiState = ContenidoUiState(
            titulo = "Lectura guiada",
            contenido = "Lee el texto y responde."
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