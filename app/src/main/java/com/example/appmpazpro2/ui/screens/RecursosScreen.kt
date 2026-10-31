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
fun RecursosScreen(
    objetivo: String,
    onRecursoClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = objetivo,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Recursos disponibles",
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
                    text = "Contenido de apoyo",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Texto explicativo relacionado con el objetivo de aprendizaje."
                )

                Button(
                    onClick = onRecursoClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver contenido")
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
                    text = "Actividad",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Actividad práctica para aplicar lo aprendido."
                )

                Button(
                    onClick = onRecursoClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Realizar actividad")
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
                    text = "Evaluación",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Evaluación breve asociada al objetivo de aprendizaje."
                )

                Button(
                    onClick = onRecursoClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver evaluación")
                }
            }
        }
    }
}