package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appmpazpro2.model.ContenidoErrores
import com.example.appmpazpro2.model.ContenidoUiState
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import com.example.appmpazpro2.ui.components.BarraVolver
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
@Composable
fun FormularioContenidoScreen(
    uiState: ContenidoUiState,
    errores: ContenidoErrores,
    esEdicion: Boolean = false,
    onAsignaturaChange: (String) -> Unit,
    onUnidadChange: (String) -> Unit,
    onObjetivoChange: (String) -> Unit,
    onTipoChange: (String) -> Unit,
    onTituloChange: (String) -> Unit,
    onContenidoChange: (String) -> Unit,
    onPreguntaChange: (String) -> Unit,
    onImagenChange: (String?) -> Unit,
    onVolverClick: () -> Unit,
    onGuardarClick: () -> Unit,
    puedeEditarInformacionAcademica: Boolean,
) {

    val context = LocalContext.current

    var imagenSeleccionada by remember {
        mutableStateOf<Uri?>(null)
    }
    val selectorImagen = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->

        imagenSeleccionada = uri
        onImagenChange(uri?.toString())
    }

    val bitmapSeleccionado = remember(imagenSeleccionada) {

        imagenSeleccionada?.let { uri ->

            try {

                context.contentResolver
                    .openInputStream(uri)
                    ?.use { inputStream ->

                        BitmapFactory
                            .decodeStream(inputStream)
                            ?.asImageBitmap()
                    }

            } catch (e: Exception) {

                null
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        BarraVolver(
            titulo = if (esEdicion) {
                "Editar contenido"
            } else {
                "Nuevo contenido"
            },
            onVolverClick = onVolverClick
        )


        Text(
            text = "2° Básico", style = MaterialTheme.typography.titleMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                Text(
                    text = "Información académica", style = MaterialTheme.typography.titleMedium
                )

                OutlinedTextField(
                    value = uiState.asignatura,
                    onValueChange = onAsignaturaChange,
                    label = {
                        Text("Asignatura")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = !puedeEditarInformacionAcademica
                )

                if (errores.asignatura != null) {
                    Text(
                        text = errores.asignatura, style = MaterialTheme.typography.bodySmall
                    )
                }

                OutlinedTextField(
                    value = uiState.unidad,
                    onValueChange = onUnidadChange,
                    label = {
                        Text("Unidad")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = !puedeEditarInformacionAcademica
                )

                if (errores.unidad != null) {
                    Text(
                        text = errores.unidad, style = MaterialTheme.typography.bodySmall
                    )
                }

                OutlinedTextField(
                    value = uiState.objetivo,
                    onValueChange = onObjetivoChange,
                    label = {
                        Text("Objetivo de aprendizaje")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = !puedeEditarInformacionAcademica
                )

                if (errores.objetivo != null) {
                    Text(
                        text = errores.objetivo, style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                Text(
                    text = "Información del contenido", style = MaterialTheme.typography.titleMedium
                )

                OutlinedTextField(
                    value = uiState.tipo, onValueChange = onTipoChange, label = {
                    Text("Tipo de contenido")
                }, singleLine = true, isError = errores.tipo != null, trailingIcon = {
                    if (errores.tipo != null) {
                        Icon(
                            imageVector = Icons.Default.Error, contentDescription = "Error"
                        )
                    }
                }, modifier = Modifier.fillMaxWidth()
                )

                if (errores.tipo != null) {
                    Text(
                        text = errores.tipo, style = MaterialTheme.typography.bodySmall
                    )
                }

                OutlinedTextField(
                    value = uiState.titulo, onValueChange = onTituloChange, label = {
                    Text("Título")
                }, singleLine = true, isError = errores.titulo != null, trailingIcon = {
                    if (errores.titulo != null) {
                        Icon(
                            imageVector = Icons.Default.Error, contentDescription = "Error"
                        )
                    }
                }, modifier = Modifier.fillMaxWidth()
                )

                if (errores.titulo != null) {
                    Text(
                        text = errores.titulo, style = MaterialTheme.typography.bodySmall
                    )
                }

                OutlinedTextField(
                    value = uiState.contenido,
                    onValueChange = onContenidoChange,
                    label = {
                        Text("Contenido")
                    },
                    minLines = 2,
                    isError = errores.contenido != null,
                    trailingIcon = {
                        if (errores.contenido != null) {
                            Icon(
                                imageVector = Icons.Default.Error, contentDescription = "Error"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                if (errores.contenido != null) {
                    Text(
                        text = errores.contenido, style = MaterialTheme.typography.bodySmall
                    )
                }

                Button(
                    onClick = {
                        selectorImagen.launch("image/*")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Seleccionar imagen de galería")
                }

                bitmapSeleccionado?.let { bitmap ->

                    Image(
                        bitmap = bitmap,
                        contentDescription = "Imagen seleccionada",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                OutlinedTextField(
                    value = uiState.pregunta,
                    onValueChange = onPreguntaChange,
                    label = {
                        Text("Pregunta")
                    },
                    placeholder = {
                        Text(
                            "Ej: ¿Por qué el personaje actuó de esa forma?"
                        )
                    },
                    minLines = 2,
                    isError = errores.pregunta != null,
                    trailingIcon = {
                        if (errores.pregunta != null) {
                            Icon(
                                imageVector = Icons.Default.Error,
                                contentDescription = "Error"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                if (errores.pregunta != null) {
                    Text(
                        text = errores.pregunta,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        Button(
            onClick = onGuardarClick, modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                if (esEdicion) {
                    "Guardar cambios"
                } else {
                    "Guardar contenido"
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormularioContenidoScreenPreview() {

    FormularioContenidoScreen(
        uiState = ContenidoUiState(
            asignatura = "Matemática",
            unidad = "Unidad 1",
            objetivo = "Resolver problemas utilizando números y operaciones.",
            tipo = "Actividad",
            titulo = "Ejercicios de números",
            contenido = "Resuelve los siguientes ejercicios.",
            pregunta = "¿Qué operación debes realizar para resolver el ejercicio?"
        ),

        errores = ContenidoErrores(),

        esEdicion = false,

        puedeEditarInformacionAcademica = false,

        onAsignaturaChange = {},
        onUnidadChange = {},
        onObjetivoChange = {},
        onTipoChange = {},
        onVolverClick = {},
        onTituloChange = {},
        onContenidoChange = {},
        onPreguntaChange = {},
        onGuardarClick = {},
        onImagenChange = {}

    )
}