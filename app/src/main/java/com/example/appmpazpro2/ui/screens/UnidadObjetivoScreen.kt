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
fun UnidadObjetivoScreen(
    asignatura: String,
    onObjetivoClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = asignatura,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Unidad 1",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Objetivo de Aprendizaje",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Comprender y desarrollar los contenidos principales de la unidad.",
            style = MaterialTheme.typography.bodyLarge
        )

        Button(
            onClick = onObjetivoClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver contenidos")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnidadObjetivoScreenPreview() {
    UnidadObjetivoScreen(
        asignatura = "Lenguaje",
        onObjetivoClick = {}
    )
}