package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SeleccionPerfilScreen(
    onUtpClick: () -> Unit,
    onDocenteClick: () -> Unit,
    onEstudianteClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "MPAZ PRO 2",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Selecciona tu perfil",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(
                top = 8.dp,
                bottom = 32.dp
            )
        )

        Button(
            onClick = onUtpClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.SupervisorAccount,
                contentDescription = "Jefatura UTP"
            )

            Text(
                text = "Jefatura UTP",
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Button(
            onClick = onDocenteClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.School,
                contentDescription = "Docente"
            )

            Text(
                text = "Docente",
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Button(
            onClick = onEstudianteClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Estudiante"
            )

            Text(
                text = "Estudiante",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SeleccionPerfilScreenPreview() {
    SeleccionPerfilScreen(
        onUtpClick = {},
        onDocenteClick = {},
        onEstudianteClick = {}
    )
}