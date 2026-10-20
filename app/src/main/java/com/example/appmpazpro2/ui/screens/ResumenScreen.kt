package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appmpazpro2.viewmodel.UsuarioViewModel

@Composable
fun ResumenScreen(
    viewModel: UsuarioViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text("Resumen de registro")

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Nombre: ${uiState.nombre}"
        )

        Text(
            text = "Correo: ${uiState.correo}"
        )

        Text(
            text = "Direccion: ${uiState.direccion}"
        )

        Text(
            text = if (uiState.aceptaTerminos) {
                "Terminos aceptados"
            } else {
                "Terminos no aceptados"
            }
        )
    }
}