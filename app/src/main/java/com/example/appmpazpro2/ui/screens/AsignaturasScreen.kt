package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AsignaturasScreen(
    mostrarTodas: Boolean = true,
    onMatematicaClick: () -> Unit,
    onLenguajeClick: () -> Unit,
    onCienciasClick: () -> Unit,
    onHistoriaClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        Text(
            text = "Asignaturas",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "2° Básico",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Elige una asignatura para comenzar",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        TarjetaAsignatura(
            emoji = "➕➖",
            titulo = "Matemática",
            descripcion = "Números, operaciones y desafíos",
            color = Color(0xFFDDEBFF),
            onClick = onMatematicaClick
        )

        if (mostrarTodas) {

            TarjetaAsignatura(
                emoji = "📚",
                titulo = "Lenguaje",
                descripcion = "Lectura, escritura y comunicación",
                color = Color(0xFFFFDFEF),
                onClick = onLenguajeClick
            )

            TarjetaAsignatura(
                emoji = "🧪",
                titulo = "Ciencias",
                descripcion = "Descubre el mundo y la naturaleza",
                color = Color(0xFFDDF5E1),
                onClick = onCienciasClick
            )

            TarjetaAsignatura(
                emoji = "🌎",
                titulo = "Historia",
                descripcion = "Nuestra comunidad y nuestro entorno",
                color = Color(0xFFFFF0C9),
                onClick = onHistoriaClick
            )
        }
    }
}

@Composable
fun TarjetaAsignatura(
    emoji: String,
    titulo: String,
    descripcion: String,
    color: Color,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp,
                    vertical = 20.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            Text(
                text = emoji,
                style = MaterialTheme.typography.headlineLarge
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }

            Text(
                text = "›",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Composable
fun AsignaturasScreenPreview() {

    AsignaturasScreen(
        mostrarTodas = true,
        onMatematicaClick = {},
        onLenguajeClick = {},
        onCienciasClick = {},
        onHistoriaClick = {}
    )
}