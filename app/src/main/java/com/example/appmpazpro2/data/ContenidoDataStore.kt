package com.example.appmpazpro2.data

import android.content.Context
import android.net.Uri
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.appmpazpro2.model.ContenidoUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.contenidoDataStore by preferencesDataStore(
    name = "contenido_preferencias"
)

class ContenidoDataStore(
    private val context: Context
) {

    companion object {
        private val CONTENIDOS =
            stringPreferencesKey("contenidos_guardados")

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

    suspend fun guardarContenidos(
        contenidos: List<ContenidoUiState>
    ) {

        context.contenidoDataStore.edit { preferencias ->

            preferencias[CONTENIDOS] =
                codificarContenidos(contenidos)
        }
    }

    private fun codificarContenidos(
        contenidos: List<ContenidoUiState>
    ): String {

        return contenidos.joinToString(
            SEPARADOR_REGISTRO
        ) { contenido ->

            listOf(
                contenido.asignatura,
                contenido.unidad,
                contenido.objetivo,
                contenido.tipo,
                contenido.titulo,
                contenido.contenido
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

        return valor
            .split(SEPARADOR_REGISTRO)
            .mapNotNull { registro ->

                val campos =
                    registro
                        .split(SEPARADOR_CAMPO)
                        .map { campo ->
                            Uri.decode(campo)
                        }

                if (campos.size != 6) {
                    return@mapNotNull null
                }

                ContenidoUiState(
                    asignatura = campos[0],
                    unidad = campos[1],
                    objetivo = campos[2],
                    tipo = campos[3],
                    titulo = campos[4],
                    contenido = campos[5]
                )
            }
    }
}