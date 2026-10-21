package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appmpazpro2.model.UsuarioErrores
import com.example.appmpazpro2.model.UsuarioUiState

@Composable
fun RegistroScreen(
    estado: UsuarioUiState,
    errores: UsuarioErrores,
    onNombreChange: (String) -> Unit,
    onCorreoChange: (String) -> Unit,
    onClaveChange: (String) -> Unit,
    onDireccionChange: (String) -> Unit,
    onAceptaTerminosChange: (Boolean) -> Unit,
    onRegistrarClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Registro",
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value = estado.nombre,
            onValueChange = onNombreChange,
            label = { Text("Nombre") },
            isError = errores.nombre != null,
            supportingText = {
                errores.nombre?.let {
                    Text(it)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estado.correo,
            onValueChange = onCorreoChange,
            label = { Text("Correo") },
            isError = errores.correo != null,
            supportingText = {
                errores.correo?.let {
                    Text(it)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estado.clave,
            onValueChange = onClaveChange,
            label = { Text("Clave") },
            visualTransformation = PasswordVisualTransformation(),
            isError = errores.clave != null,
            supportingText = {
                errores.clave?.let {
                    Text(it)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = estado.direccion,
            onValueChange = onDireccionChange,
            label = { Text("Dirección") },
            isError = errores.direccion != null,
            supportingText = {
                errores.direccion?.let {
                    Text(it)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = estado.aceptaTerminos,
                onCheckedChange = onAceptaTerminosChange
            )

            Text("Acepto los términos")
        }

        Button(
            onClick = onRegistrarClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistroScreenPreview() {
    RegistroScreen(
        estado = UsuarioUiState(),
        errores = UsuarioErrores(),
        onNombreChange = {},
        onCorreoChange = {},
        onClaveChange = {},
        onDireccionChange = {},
        onAceptaTerminosChange = {},
        onRegistrarClick = {}
    )
}