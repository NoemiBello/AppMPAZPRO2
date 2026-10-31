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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UnidadObjetivoScreen(
    asignatura: String,
    onObjetivoClick: () -> Unit
) {

    val unidad1 = when (asignatura) {

        "Matematica" ->
            "Números y operaciones"

        "Lenguaje" ->
            "Lectura"

        "Ciencias Naturales" ->
            "Ciencias de la vida"

        "Historia" ->
            "Historia y comunidad"

        else ->
            "Unidad de aprendizaje"
    }

    val unidad2 = when (asignatura) {

        "Matematica" ->
            "Patrones y álgebra"

        "Lenguaje" ->
            "Escritura"

        "Ciencias Naturales" ->
            "Ciencias físicas"

        "Historia" ->
            "Geografía y entorno"

        else ->
            "Unidad de aprendizaje"
    }

    val unidad3 = when (asignatura) {

        "Matematica" ->
            "Geometría"

        "Lenguaje" ->
            "Comunicación oral"

        "Ciencias Naturales" ->
            "Tierra y universo"

        "Historia" ->
            "Formación ciudadana"

        else ->
            "Unidad de aprendizaje"
    }

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
            text = "2° Básico",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Selecciona una unidad",
            style = MaterialTheme.typography.bodyLarge
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "Unidad 1",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = unidad1
                )

                Button(
                    onClick = onObjetivoClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver objetivos")
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
                    text = "Unidad 2",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = unidad2
                )

                Button(
                    onClick = onObjetivoClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver objetivos")
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
                    text = "Unidad 3",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = unidad3
                )

                Button(
                    onClick = onObjetivoClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ver objetivos")
                }
            }
        }
    }
}