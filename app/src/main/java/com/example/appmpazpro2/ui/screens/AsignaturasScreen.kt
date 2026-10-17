package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AsignaturasScreen() {

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
            onClick = { }
        ) {
            Text("Lenguaje")
        }

        Button(
            onClick = { }
        ) {
            Text("Matematica")
        }

        Button(
            onClick = { }
        ) {
            Text("Ciencias Naturales")
        }

        Button(
            onClick = { }
        ) {
            Text("Historia")
        }
    }
}