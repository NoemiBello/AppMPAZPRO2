package com.example.appmpazpro2.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "estado_preferencias")

class EstadoDataStore(private val context: Context) {

    companion object {
        private val MODO_ACTIVADO = booleanPreferencesKey("modo_activado")
    }

    fun obtenerEstado(): Flow<Boolean> {
        return context.dataStore.data.map { preferencias ->
            preferencias[MODO_ACTIVADO] ?: false
        }
    }

    suspend fun guardarEstado(activado: Boolean) {
        context.dataStore.edit { preferencias ->
            preferencias[MODO_ACTIVADO] = activado
        }
    }
}