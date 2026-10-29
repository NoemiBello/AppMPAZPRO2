package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AsignaturasScreen(
    onLenguajeClick: () -> Unit,
    onMatematicaClick: () -> Unit,
    onCienciasClick: () -> Unit,
    onHistoriaClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Asignaturas",
            style = MaterialTheme.typography.headlineMedium
        )

        Button(
            onClick = onLenguajeClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lenguaje")
        }

        Button(
            onClick = onMatematicaClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Matemática")
        }

        Button(
            onClick = onCienciasClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ciencias Naturales")
        }

        Button(
            onClick = onHistoriaClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Historia")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AsignaturasScreenPreview() {
    AsignaturasScreen(
        onLenguajeClick = {},
        onMatematicaClick = {},
        onCienciasClick = {},
        onHistoriaClick = {}
    )
}