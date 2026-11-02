package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.ui.draw.clip
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.appmpazpro2.model.ContenidoUiState
import com.example.appmpazpro2.model.EstadoRespuesta
import com.example.appmpazpro2.model.RespuestaUiState
import com.example.appmpazpro2.ui.components.BarraVolver
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.AnimatedVisibility

@Composable
fun ActividadesEstudianteScreen(
    asignatura: String,
    unidad: String,
    objetivo: String,
    contenidos: List<ContenidoUiState>,
    respuestas: List<RespuestaUiState>,
    onVolverClick: () -> Unit,
    onActividadClick: (ContenidoUiState) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        BarraVolver(
            titulo = asignatura,
            onVolverClick = onVolverClick
        )

        Text(
            text = unidad,
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = objetivo,
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF5F5968)
        )
        AnimatedVisibility(
            visible = contenidos.isNotEmpty(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = "Actividades disponibles",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        if (contenidos.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 70.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {

                Box(
                    modifier = Modifier
                        .size(
                            width = 170.dp,
                            height = 120.dp
                        )
                        .clip(
                            RoundedCornerShape(
                                50.dp
                            )
                        )
                        .background(
                            Color(0xFFF0E9FF)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.AutoStories,
                        contentDescription = "Sin contenido",
                        modifier = Modifier.size(72.dp),
                        tint = Color(0xFF6848D7)
                    )
                }

                Text(
                    text = "Aún no hay contenido\ndisponible",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF211D2B)
                )

                Text(
                    text = "El docente o UTP aún no ha\npublicado una actividad\npara este objetivo.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF6D6675),
                    lineHeight = 23.sp,
                    modifier = Modifier.padding(
                        horizontal = 32.dp
                    )
                )
            }

        } else {


            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = contenidos,
                    key = { contenido -> contenido.id }
                ) { contenido ->

                    val respuesta =
                        respuestas
                            .filter {
                                it.contenidoId == contenido.id
                            }
                            .maxByOrNull {
                                it.id
                            }

                    OutlinedCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        border = BorderStroke(
                            1.dp,
                            Color(0xFFE0D8F5)
                        ),
                        colors = CardDefaults.outlinedCardColors(
                            containerColor = Color(0xFFF8F5FF)
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            Row(
                                horizontalArrangement =
                                    Arrangement.spacedBy(8.dp)
                            ) {

                                Icon(
                                    imageVector =
                                        Icons.Default.MenuBook,
                                    contentDescription = null,
                                    tint = Color(0xFF6246C7)
                                )

                                Text(
                                    text = contenido.titulo,
                                    style =
                                        MaterialTheme.typography.titleMedium,
                                    color = Color(0xFF2E2938)
                                )
                            }

                            if (contenido.tipo.isNotBlank()) {
                                Text(
                                    text = contenido.tipo,
                                    style =
                                        MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF6D6675)
                                )
                            }
                            AnimatedContent(
                                targetState = respuesta?.estado,
                                transitionSpec = {
                                    fadeIn() togetherWith fadeOut()
                                },
                                label = "estadoActividad"
                            ) { estado ->

                                when (estado) {

                                    EstadoRespuesta.PENDIENTE_REVISION -> {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.HourglassTop,
                                                contentDescription = null,
                                                tint = Color(0xFFB77900)
                                            )

                                            Text(
                                                text = "Pendiente de revisión",
                                                color = Color(0xFF805500),
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        }
                                    }

                                    EstadoRespuesta.CORRECTA -> {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.CheckCircle,
                                                contentDescription = null,
                                                tint = Color(0xFF2E7D32)
                                            )

                                            Text(
                                                text = "Aprobada",
                                                color = Color(0xFF2E7D32),
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        }
                                    }

                                    EstadoRespuesta.INCORRECTA -> {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Refresh,
                                                contentDescription = null,
                                                tint = Color(0xFFC62828)
                                            )

                                            Text(
                                                text = "Necesita revisión",
                                                color = Color(0xFFC62828),
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        }
                                    }

                                    null -> {
                                        Text(
                                            text = "Sin responder",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = Color(0xFF77717F)
                                        )
                                    }
                                }
                            }

                            Button(
                                onClick = {
                                    onActividadClick(contenido)
                                },
                                modifier =
                                    Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    if (respuesta == null) {
                                        "Abrir actividad"
                                    } else {
                                        "Ver actividad"
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}