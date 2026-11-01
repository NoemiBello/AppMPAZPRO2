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
    asignatura: String,
    unidad: String,
    onObjetivoClick: (String) -> Unit
) {

    val objetivos = when {

        asignatura == "Lenguaje" &&
                unidad == "Unidad 1" -> listOf(
            "Comprender textos breves",
            "Identificar informacion explicita",
            "Reconocer personajes e ideas principales"
        )

        asignatura == "Lenguaje" &&
                unidad == "Unidad 2" -> listOf(
            "Escribir oraciones simples",
            "Organizar ideas antes de escribir",
            "Revisar textos breves"
        )

        asignatura == "Lenguaje" &&
                unidad == "Unidad 3" -> listOf(
            "Expresarse oralmente",
            "Escuchar instrucciones",
            "Participar en conversaciones"
        )

        asignatura == "Matematica" -> listOf(
            "Resolver ejercicios matematicos",
            "Aplicar estrategias de calculo",
            "Resolver problemas simples"
        )

        asignatura == "Ciencias Naturales" -> listOf(
            "Observar fenomenos naturales",
            "Reconocer caracteristicas del entorno",
            "Explicar situaciones simples"
        )

        asignatura == "Historia" -> listOf(
            "Reconocer elementos de la comunidad",
            "Identificar caracteristicas del entorno",
            "Comprender normas de convivencia"
        )

        else -> listOf(
            "Objetivo de aprendizaje"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement =
            Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = asignatura,
            style =
                MaterialTheme.typography.headlineMedium
        )

        Text(
            text = unidad,
            style =
                MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Selecciona un objetivo",
            style =
                MaterialTheme.typography.bodyLarge
        )

        objetivos.forEachIndexed { index, objetivo ->

            Card(
                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier =
                        Modifier.padding(16.dp),
                    verticalArrangement =
                        Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = "Objetivo ${index + 1}",
                        style =
                            MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = objetivo
                    )

                    Button(
                        onClick = {
                            onObjetivoClick(objetivo)
                        },
                        modifier =
                            Modifier.fillMaxWidth()
                    ) {
                        Text("Seleccionar")
                    }
                }
            }
        }
    }
}