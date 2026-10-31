package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appmpazpro2.ui.theme.AmarilloClaroMPaz
import com.example.appmpazpro2.ui.theme.AzulClaroMPaz
import com.example.appmpazpro2.ui.theme.MoradoClaroMPaz
import com.example.appmpazpro2.ui.theme.VerdeClaroMPaz
import com.example.appmpazpro2.ui.utils.obtenerWindowSizeClass

@Composable
fun SeleccionPerfilScreen(
    onUtpClick: () -> Unit,
    onDocenteClick: () -> Unit,
    onEstudianteClick: () -> Unit
) {

    val windowSizeClass = obtenerWindowSizeClass()

    when (windowSizeClass.widthSizeClass) {

        WindowWidthSizeClass.Compact -> {
            SeleccionPerfilCompact(
                onUtpClick = onUtpClick,
                onDocenteClick = onDocenteClick,
                onEstudianteClick = onEstudianteClick
            )
        }

        WindowWidthSizeClass.Medium -> {
            SeleccionPerfilMedium(
                onUtpClick = onUtpClick,
                onDocenteClick = onDocenteClick,
                onEstudianteClick = onEstudianteClick
            )
        }

        else -> {
            SeleccionPerfilExpanded(
                onUtpClick = onUtpClick,
                onDocenteClick = onDocenteClick,
                onEstudianteClick = onEstudianteClick
            )
        }
    }
}

@Composable
fun LogoMPaz() {

    Card(
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = AmarilloClaroMPaz
        )
    ) {

        Column(
            modifier = Modifier.padding(
                horizontal = 30.dp,
                vertical = 16.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = Icons.Default.AutoStories,
                contentDescription = "Logo MPAZ PRO 2",
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "MPAZ",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "PRO 2",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )

            Text(
                text = "⭐ Aprende jugando ⭐",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun EncabezadoSeleccionPerfil() {

    LogoMPaz()

    Text(
        text = "¡Hola! 👋",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 18.dp)
    )

    Text(
        text = "¿Cómo quieres ingresar?",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 4.dp)
    )

    Text(
        text = "2° Básico",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(
            top = 8.dp,
            bottom = 18.dp
        )
    )
}

@Composable
fun TarjetaPerfil(
    emoji: String,
    titulo: String,
    descripcion: String,
    textoBoton: String,
    colorTarjeta: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorTarjeta
        )
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
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
                    modifier = Modifier.padding(
                        top = 4.dp,
                        bottom = 10.dp
                    )
                )

                Button(
                    onClick = onClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {

                    Text(
                        text = textoBoton,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun ContenidoPerfiles(
    onUtpClick: () -> Unit,
    onDocenteClick: () -> Unit,
    onEstudianteClick: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        TarjetaPerfil(
            emoji = "👩‍💼",
            titulo = "Jefatura UTP",
            descripcion = "Gestiona y acompaña el aprendizaje",
            textoBoton = "Ingresar como UTP",
            colorTarjeta = AzulClaroMPaz,
            onClick = onUtpClick
        )

        TarjetaPerfil(
            emoji = "👩‍🏫",
            titulo = "Docente",
            descripcion = "Organiza contenidos y actividades",
            textoBoton = "Ingresar como Docente",
            colorTarjeta = MoradoClaroMPaz,
            onClick = onDocenteClick
        )

        TarjetaPerfil(
            emoji = "🧒",
            titulo = "Estudiante",
            descripcion = "Aprende, juega y completa tus actividades",
            textoBoton = "¡Comenzar!",
            colorTarjeta = VerdeClaroMPaz,
            onClick = onEstudianteClick
        )
    }
}

@Composable
fun SeleccionPerfilCompact(
    onUtpClick: () -> Unit,
    onDocenteClick: () -> Unit,
    onEstudianteClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        EncabezadoSeleccionPerfil()

        ContenidoPerfiles(
            onUtpClick = onUtpClick,
            onDocenteClick = onDocenteClick,
            onEstudianteClick = onEstudianteClick
        )
    }
}

@Composable
fun SeleccionPerfilMedium(
    onUtpClick: () -> Unit,
    onDocenteClick: () -> Unit,
    onEstudianteClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 80.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        EncabezadoSeleccionPerfil()

        ContenidoPerfiles(
            onUtpClick = onUtpClick,
            onDocenteClick = onDocenteClick,
            onEstudianteClick = onEstudianteClick
        )
    }
}

@Composable
fun SeleccionPerfilExpanded(
    onUtpClick: () -> Unit,
    onDocenteClick: () -> Unit,
    onEstudianteClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        EncabezadoSeleccionPerfil()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                TarjetaPerfil(
                    emoji = "👩‍💼",
                    titulo = "Jefatura UTP",
                    descripcion = "Gestiona y acompaña el aprendizaje",
                    textoBoton = "Ingresar como UTP",
                    colorTarjeta = AzulClaroMPaz,
                    onClick = onUtpClick
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {

                TarjetaPerfil(
                    emoji = "👩‍🏫",
                    titulo = "Docente",
                    descripcion = "Organiza contenidos y actividades",
                    textoBoton = "Ingresar como Docente",
                    colorTarjeta = MoradoClaroMPaz,
                    onClick = onDocenteClick
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {

                TarjetaPerfil(
                    emoji = "🧒",
                    titulo = "Estudiante",
                    descripcion = "Aprende y completa tus actividades",
                    textoBoton = "¡Comenzar!",
                    colorTarjeta = VerdeClaroMPaz,
                    onClick = onEstudianteClick
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Composable
fun SeleccionPerfilCompactPreview() {

    SeleccionPerfilCompact(
        onUtpClick = {},
        onDocenteClick = {},
        onEstudianteClick = {}
    )
}

@Preview(
    showBackground = true,
    widthDp = 700,
    heightDp = 900
)
@Composable
fun SeleccionPerfilMediumPreview() {

    SeleccionPerfilMedium(
        onUtpClick = {},
        onDocenteClick = {},
        onEstudianteClick = {}
    )
}

@Preview(
    showBackground = true,
    widthDp = 1000,
    heightDp = 700
)
@Composable
fun SeleccionPerfilExpandedPreview() {

    SeleccionPerfilExpanded(
        onUtpClick = {},
        onDocenteClick = {},
        onEstudianteClick = {}
    )
}