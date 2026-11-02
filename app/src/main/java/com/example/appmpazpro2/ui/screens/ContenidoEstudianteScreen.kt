package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.QuestionAnswer
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
import androidx.compose.ui.unit.dp
import com.example.appmpazpro2.model.EstadoRespuesta


@Composable
fun ContenidoEstudianteScreen(
    titulo: String,
    contenido: String,
    pregunta: String,
    respuesta: String,
    intentosUsados: Int,
    maxIntentos: Int,
    puedeResponder: Boolean,
    hayContenido: Boolean,
    respuestaEnviada: String?,
    estadoRespuesta: EstadoRespuesta?,
    onRespuestaChange: (String) -> Unit,
    onEnviarClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFBFAFF))
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = 16.dp, vertical = 14.dp
            ), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        if (!hayContenido) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF2EEFF)
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {

                    Text(
                        text = "Aún no hay actividad disponible",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF5E35B1)
                    )

                    Text(
                        text = "Cuando exista una actividad aprobada para este objetivo aparecerá aquí.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF55515E)
                    )
                }
            }

            return@Column
        }


        Text(
            text = titulo, style = MaterialTheme.typography.headlineSmall, color = Color(0xFF2C2933)
        )


        // CONTENIDO

        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            border = BorderStroke(
                1.dp, Color(0xFFE1D9F6)
            ),
            colors = CardDefaults.outlinedCardColors(
                containerColor = Color(0xFFF3EEFF)
            )
        ) {

            Column(
                modifier = Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.EditNote,
                        contentDescription = null,
                        tint = Color(0xFF6C4CCF)
                    )

                    Text(
                        text = "Contenido",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF5036A4)
                    )
                }

                Text(
                    text = contenido,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF34313A)
                )
            }
        }


        // PREGUNTA

        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            border = BorderStroke(
                1.dp, Color(0xFFE3DDF0)
            ),
            colors = CardDefaults.outlinedCardColors(
                containerColor = Color(0xFFF8F5FC)
            )
        ) {

            Column(
                modifier = Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.QuestionAnswer,
                        contentDescription = null,
                        tint = Color(0xFF6C4CCF)
                    )

                    Text(
                        text = "Pregunta",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF5036A4)
                    )
                }

                Text(
                    text = if (pregunta.isBlank()) {
                        "Responde la actividad."
                    } else {
                        pregunta
                    }, style = MaterialTheme.typography.bodyLarge, color = Color(0xFF34313A)
                )
            }
        }


        // TODAVÍA NO RESPONDE

        if (respuestaEnviada == null) {

            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                border = BorderStroke(
                    1.dp, Color(0xFFE3DDF0)
                ),
                colors = CardDefaults.outlinedCardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Text(
                        text = "Tu respuesta",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF5036A4)
                    )

                    OutlinedTextField(
                        value = respuesta,
                        onValueChange = onRespuestaChange,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(
                                "Escribe tu respuesta aquí"
                            )
                        },
                        minLines = 3,
                        enabled = puedeResponder,
                        shape = RoundedCornerShape(14.dp)
                    )

                    Text(
                        text = "Intentos utilizados: $intentosUsados de $maxIntentos",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF706A78)
                    )
                }
            }


            Button(
                onClick = onEnviarClick,
                enabled = puedeResponder && respuesta.isNotBlank(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6C4CCF)
                )
            ) {

                Text(
                    text = "Enviar respuesta"
                )
            }


            if (!puedeResponder) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFE5E5)
                    )
                ) {

                    Text(
                        text = "Has alcanzado el máximo de intentos.",
                        color = Color(0xFFB3261E),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(14.dp)
                    )
                }
            }

        } else {


            // RESPUESTA ENVIADA

            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                border = BorderStroke(
                    1.dp, Color(0xFFE3DDF0)
                ),
                colors = CardDefaults.outlinedCardColors(
                    containerColor = Color(0xFFF8F5FC)
                )
            ) {

                Column(
                    modifier = Modifier.padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {

                    Text(
                        text = "Tu respuesta",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF5036A4)
                    )

                    Text(
                        text = respuestaEnviada,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF34313A)
                    )
                }
            }


            // ESTADO

            when (estadoRespuesta) {

                EstadoRespuesta.PENDIENTE_REVISION -> {

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFF3CD)
                        )
                    ) {

                        Row(
                            modifier = Modifier.padding(15.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {

                            Icon(
                                imageVector = Icons.Default.HourglassTop,
                                contentDescription = null,
                                tint = Color(0xFFB77900)
                            )

                            Column {

                                Text(
                                    text = "Pendiente de revisión",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color(0xFF805500)
                                )

                                Text(
                                    text = "Tu respuesta fue enviada correctamente.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF6A5000)
                                )
                            }
                        }
                    }
                }


                EstadoRespuesta.CORRECTA -> {

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFE2F5E6)
                        )
                    ) {

                        Row(
                            modifier = Modifier.padding(15.dp),
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
                                    text = "¡Muy bien! Tu respuesta fue aprobada.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF39613C)
                                )
                            }
                        }
                    }
                }


                EstadoRespuesta.INCORRECTA -> {

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFE5E5)
                        )
                    ) {

                        Row(
                            modifier = Modifier.padding(15.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {

                            Icon(
                                imageVector = Icons.Default.Error,
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
                                    text = "Revisa tu respuesta e inténtalo nuevamente si corresponde.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF7E3737)
                                )
                            }
                        }
                    }
                }

                null -> Unit
            }
        }
    }
}