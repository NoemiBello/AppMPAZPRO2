package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ObjetivosScreen(
    unidad: String,
    onContenidoClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = unidad,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Objetivos de aprendizaje",
            style = MaterialTheme.typography.titleLarge
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Objetivo 1",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Resolver problemas utilizando números y operaciones."
                )

                Button(
                    onClick = onContenidoClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver contenidos")
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Objetivo 2",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Aplicar estrategias de resolución en situaciones matemáticas."
                )

                Button(
                    onClick = onContenidoClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver contenidos")
                }
            }
        }
    }
}