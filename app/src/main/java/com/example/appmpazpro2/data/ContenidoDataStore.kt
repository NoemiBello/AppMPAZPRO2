package com.example.appmpazpro2.data

import android.content.Context
import android.net.Uri
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.appmpazpro2.model.ContenidoUiState
import com.example.appmpazpro2.model.EstadoContenido
import com.example.appmpazpro2.model.EstadoRespuesta
import com.example.appmpazpro2.model.RespuestaUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.contenidoDataStore by preferencesDataStore(
    name = "contenido_preferencias"
)

class ContenidoDataStore(
    private val context: Context
) {

    companion object {

        private val CONTENIDOS = stringPreferencesKey("contenidos_guardados")

        private val RESPUESTAS = stringPreferencesKey("respuestas_guardadas")

        private const val SEPARADOR_REGISTRO = ";;"
        private const val SEPARADOR_CAMPO = "|"
    }

    fun obtenerContenidos(): Flow<List<ContenidoUiState>> {

        return context.contenidoDataStore.data.map { preferencias ->

            decodificarContenidos(
                preferencias[CONTENIDOS].orEmpty()
            )
        }
    }

    fun obtenerRespuestas(): Flow<List<RespuestaUiState>> {

        return context.contenidoDataStore.data.map { preferencias ->

            decodificarRespuestas(
                preferencias[RESPUESTAS].orEmpty()
            )
        }
    }



    suspend fun guardarContenidos(
        contenidos: List<ContenidoUiState>
    ) {

        context.contenidoDataStore.edit { preferencias ->

            preferencias[CONTENIDOS] = codificarContenidos(contenidos)
        }
    }

    suspend fun guardarRespuestas(
        respuestas: List<RespuestaUiState>
    ) {

        context.contenidoDataStore.edit { preferencias ->

            preferencias[RESPUESTAS] = codificarRespuestas(respuestas)
        }
    }

    private fun codificarContenidos(
        contenidos: List<ContenidoUiState>
    ): String {

        return contenidos.joinToString(
            SEPARADOR_REGISTRO
        ) { contenido ->

            listOf(
                contenido.id.toString(),
                contenido.asignatura,
                contenido.unidad,
                contenido.objetivo,
                contenido.tipo,
                contenido.titulo,
                contenido.contenido,
                contenido.pregunta,
                contenido.estado.name
            ).joinToString(
                SEPARADOR_CAMPO
            ) { valor ->
                Uri.encode(valor)
            }
        }
    }

    private fun decodificarContenidos(
        valor: String
    ): List<ContenidoUiState> {

        if (valor.isBlank()) {
            return emptyList()
        }

        return valor.split(SEPARADOR_REGISTRO).mapNotNull { registro ->

                val campos = registro.split(SEPARADOR_CAMPO).map { campo ->
                        Uri.decode(campo)
                    }

                when (campos.size) {

                    // Formato nuevo: incluye pregunta
                    9 -> {

                        ContenidoUiState(
                            id = campos[0].toIntOrNull() ?: 0,
                            asignatura = campos[1],
                            unidad = campos[2],
                            objetivo = campos[3],
                            tipo = campos[4],
                            titulo = campos[5],
                            contenido = campos[6],
                            pregunta = campos[7],
                            estado = runCatching {
                                EstadoContenido.valueOf(campos[8])
                            }.getOrDefault(
                                EstadoContenido.PENDIENTE
                            )
                        )
                    }

                    // Formato antiguo: todavía no tenía pregunta
                    8 -> {

                        ContenidoUiState(
                            id = campos[0].toIntOrNull() ?: 0,
                            asignatura = campos[1],
                            unidad = campos[2],
                            objetivo = campos[3],
                            tipo = campos[4],
                            titulo = campos[5],
                            contenido = campos[6],
                            pregunta = "",
                            estado = runCatching {
                                EstadoContenido.valueOf(campos[7])
                            }.getOrDefault(
                                EstadoContenido.PENDIENTE
                            )
                        )
                    }

                    else -> null
                }
            }
    }

    private fun codificarRespuestas(
        respuestas: List<RespuestaUiState>
    ): String {

        return respuestas.joinToString(
            SEPARADOR_REGISTRO
        ) { respuesta ->

            listOf(
                respuesta.id.toString(),
                respuesta.contenidoId.toString(),
                respuesta.respuesta,
                respuesta.estado.name
            ).joinToString(
                SEPARADOR_CAMPO
            ) { valor ->
                Uri.encode(valor)
            }
        }
    }

    private fun decodificarRespuestas(
        valor: String
    ): List<RespuestaUiState> {

        if (valor.isBlank()) {
            return emptyList()
        }

        return valor.split(SEPARADOR_REGISTRO).mapNotNull { registro ->

                val campos = registro.split(SEPARADOR_CAMPO).map { campo ->
                        Uri.decode(campo)
                    }

                if (campos.size != 4) {
                    return@mapNotNull null
                }

                RespuestaUiState(
                    id = campos[0].toIntOrNull() ?: 0,
                    contenidoId = campos[1].toIntOrNull() ?: 0,
                    respuesta = campos[2],
                    estado = runCatching {
                        EstadoRespuesta.valueOf(campos[3])
                    }.getOrDefault(
                        EstadoRespuesta.PENDIENTE_REVISION
                    )
                )
            }
    }
}