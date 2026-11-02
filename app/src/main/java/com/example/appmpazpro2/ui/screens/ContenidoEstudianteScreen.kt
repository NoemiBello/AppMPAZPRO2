package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appmpazpro2.model.EstadoRespuesta

@Composable
fun ContenidoEstudianteScreen(
    titulo: String,
    contenido: String,
    pregunta: String,
    respuesta: String,
    hayContenido: Boolean,
    respuestaEnviada: String?,
    estadoRespuesta: EstadoRespuesta?,
    onRespuestaChange: (String) -> Unit,
    onEnviarClick: () -> Unit
) {

    val morado = Color(0xFF6C4CF1)
    val moradoOscuro = Color(0xFF4F35B5)
    val lilaClaro = Color(0xFFF7F4FF)
    val lilaPregunta = Color(0xFFF1EBFF)
    val bordeLila = Color(0xFFE1D9F7)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFCFBFF))
            .padding(
                horizontal = 18.dp,
                vertical = 16.dp
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = if (hayContenido) {
                titulo
            } else {
                "Actividad"
            },
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "2° Básico",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // NO EXISTE ACTIVIDAD APROBADA
        if (!hayContenido) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lilaClaro
                )
            ) {

                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.MenuBook,
                        contentDescription = null,
                        tint = morado
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        Text(
                            text = "Aún no hay actividad disponible",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "Todavía no existe una actividad aprobada para este objetivo.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

        } else {

            // CONTENIDO
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(
                    1.dp,
                    bordeLila
                ),
                colors = CardDefaults.outlinedCardColors(
                    containerColor = lilaClaro
                )
            ) {

                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(7.dp)
                ) {

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        Icon(
                            imageVector = Icons.Default.MenuBook,
                            contentDescription = null,
                            tint = morado
                        )

                        Text(
                            text = "Contenido",
                            style = MaterialTheme.typography.titleMedium,
                            color = moradoOscuro
                        )
                    }

                    Text(
                        text = contenido,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // PREGUNTA
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(
                    1.dp,
                    bordeLila
                ),
                colors = CardDefaults.outlinedCardColors(
                    containerColor = lilaPregunta
                )
            ) {

                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(7.dp)
                ) {

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        Icon(
                            imageVector = Icons.Default.QuestionAnswer,
                            contentDescription = null,
                            tint = morado
                        )

                        Text(
                            text = "Pregunta",
                            style = MaterialTheme.typography.titleMedium,
                            color = moradoOscuro
                        )
                    }

                    Text(
                        text = pregunta,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // TODAVÍA NO RESPONDE
            if (respuestaEnviada == null) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.EditNote,
                        contentDescription = null,
                        tint = morado
                    )

                    Text(
                        text = "Tu respuesta",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                OutlinedTextField(
                    value = respuesta,
                    onValueChange = onRespuestaChange,
                    placeholder = {
                        Text("Escribe tu respuesta...")
                    },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp)
                )

                Button(
                    onClick = onEnviarClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = morado
                    )
                ) {

                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = null
                    )

                    Text(
                        text = "Enviar respuesta",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

            } else {

                // RESPUESTA YA ENVIADA
                OutlinedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(
                        1.dp,
                        bordeLila
                    ),
                    colors = CardDefaults.outlinedCardColors(
                        containerColor = Color.White
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(7.dp)
                    ) {

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            Icon(
                                imageVector = Icons.Default.EditNote,
                                contentDescription = null,
                                tint = morado
                            )

                            Text(
                                text = "Tu respuesta",
                                style = MaterialTheme.typography.titleMedium,
                                color = moradoOscuro
                            )
                        }

                        Text(
                            text = respuestaEnviada.orEmpty(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                // ESTADO REAL DE LA RESPUESTA
                when (estadoRespuesta) {

                    EstadoRespuesta.PENDIENTE_REVISION -> {

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFFFF3CD)
                            )
                        ) {

                            Row(
                                modifier = Modifier.padding(14.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {

                                Icon(
                                    imageVector = Icons.Default.AccessTime,
                                    contentDescription = null,
                                    tint = Color(0xFFE6A000)
                                )

                                Column {

                                    Text(
                                        text = "Pendiente de revisión",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = Color(0xFF8A5A00)
                                    )

                                    Text(
                                        text = "Tu respuesta fue enviada y está siendo revisada.",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }

                    EstadoRespuesta.CORRECTA -> {

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFE7F8E8)
                            )
                        ) {

                            Row(
                                modifier = Modifier.padding(14.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {

                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = Color(0xFF2E7D32)
                                )

                                Column {

                                    Text(
                                        text = "Aprobada",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = Color(0xFF2E7D32)
                                    )

                                    Text(
                                        text = "¡Muy bien! Comprendiste el contenido.",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }

                    EstadoRespuesta.INCORRECTA -> {

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFFFE6E6)
                            )
                        ) {

                            Row(
                                modifier = Modifier.padding(14.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {

                                Icon(
                                    imageVector = Icons.Default.EditNote,
                                    contentDescription = null,
                                    tint = Color(0xFFC62828)
                                )

                                Column {

                                    Text(
                                        text = "Necesita revisión",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = Color(0xFFC62828)
                                    )

                                    Text(
                                        text = "Tu respuesta fue revisada y necesita corrección.",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }

                    null -> {
                        // No muestra estado si aún no existe respuesta.
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ContenidoEstudianteScreenPreview() {

    ContenidoEstudianteScreen(
        titulo = "El ganso despistado",
        contenido = "Había una vez un ganso muy curioso.",
        pregunta = "¿Por qué el ganso andaba despistado?",
        respuesta = "",
        hayContenido = true,
        respuestaEnviada = null,
        estadoRespuesta = null,
        onRespuestaChange = {},
        onEnviarClick = {}
    )
}