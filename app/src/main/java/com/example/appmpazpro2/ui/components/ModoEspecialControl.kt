package com.example.appmpazpro2.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
@Composable
fun ModoEspecialControl(
    activado: Boolean,
    onCambiarEstado: () -> Unit
) {
    var estadoGuardado by rememberSaveable(activado) {
        mutableStateOf(activado)
    }
    val textoBoton by remember(estadoGuardado) {
        derivedStateOf {
            if (estadoGuardado) "Desactivar modo especial"
            else "Activar modo especial"
        }
    }

    val colorTexto by animateColorAsState(
        targetValue = if (estadoGuardado) Color.Green else Color.Red,
        label = "colorEstado"
    )

    Column {
        Text(
            text = if (estadoGuardado) "Modo especial activado" else "Modo especial desactivado",
            color = colorTexto,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                estadoGuardado = !estadoGuardado
                onCambiarEstado()
            }
        ) {
            Text(text = textoBoton)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ModoEspecialControlPreview() {
    ModoEspecialControl(
        activado = false,
        onCambiarEstado = {}
    )
}