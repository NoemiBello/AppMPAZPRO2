package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        if (!hayContenido) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = "Aún no hay actividad disponible",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "Cuando exista una actividad aprobada para este objetivo aparecerá aquí.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            return@Column
        }


        Text(
            text = titulo,
            style = MaterialTheme.typography.headlineSmall
        )


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF1ECFF)
            )
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.EditNote,
                    contentDescription = null
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Contenido",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = contenido,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Icon(
                    imageVector = Icons.Default.QuestionAnswer,
                    contentDescription = null
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Pregunta",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = if (pregunta.isBlank()) {
                        "Responde la actividad."
                    } else {
                        pregunta
                    },
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }


        if (respuestaEnviada == null) {

            OutlinedTextField(
                value = respuesta,
                onValueChange = onRespuestaChange,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Tu respuesta")
                },
                placeholder = {
                    Text("Escribe tu respuesta aquí")
                },
                minLines = 3,
                enabled = puedeResponder
            )


            Text(
                text = "Intentos: $intentosUsados de $maxIntentos",
                style = MaterialTheme.typography.bodySmall
            )


            Button(
                onClick = onEnviarClick,
                enabled =
                    puedeResponder &&
                            respuesta.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Enviar respuesta")
            }


            if (!puedeResponder) {

                Text(
                    text = "Has alcanzado el máximo de intentos.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        } else {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "Tu respuesta",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = respuestaEnviada,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }


            when (estadoRespuesta) {

                EstadoRespuesta.PENDIENTE_REVISION -> {

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFF3CD)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Icon(
                                imageVector = Icons.Default.HourglassTop,
                                contentDescription = null
                            )

                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )

                            Text(
                                text = "Pendiente de revisión",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }


                EstadoRespuesta.CORRECTA -> {

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFDDF5E3)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null
                            )

                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )

                            Text(
                                text = "Aprobada",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }


                EstadoRespuesta.INCORRECTA -> {

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFE0E0)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = null
                            )

                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )

                            Text(
                                text = "Necesita revisión",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }


                null -> Unit
            }
        }
    }
}