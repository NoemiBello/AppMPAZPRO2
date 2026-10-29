package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ContenidoEstudianteScreen(
    titulo: String,
    contenido: String,
    respuesta: String,
    onRespuestaChange: (String) -> Unit,
    onEnviarClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = titulo,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = contenido,
            style = MaterialTheme.typography.bodyLarge
        )

        OutlinedTextField(
            value = respuesta,
            onValueChange = onRespuestaChange,
            label = {
                Text("Escribe tu respuesta")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onEnviarClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar respuesta")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContenidoEstudianteScreenPreview() {
    ContenidoEstudianteScreen(
        titulo = "Lectura guiada",
        contenido = "Lee el texto y responde la pregunta.",
        respuesta = "",
        onRespuestaChange = {},
        onEnviarClick = {}
    )
}