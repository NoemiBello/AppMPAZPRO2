package com.example.appmpazpro2.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.example.appmpazpro2.ui.components.ImagenInteligente
import com.example.appmpazpro2.viewmodel.PerfilViewModel
import java.io.File

@Composable
fun PerfilScreen(
    viewModel: PerfilViewModel
) {

    val context = LocalContext.current

    val imagenUri by viewModel.imagenUri.collectAsState()

    val imagenCamaraTemporal =
        remember {
            mutableStateOf<Uri?>(null)
        }

    val galeriaLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->

            viewModel.actualizarDesdeGaleria(uri)
        }

    val camaraLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture()
        ) { exito ->

            if (exito) {
                viewModel.actualizarDesdeCamara(
                    imagenCamaraTemporal.value
                )
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text("Perfil")

        ImagenInteligente(
            imagenUri = imagenUri
        )

        Button(
            onClick = {
                galeriaLauncher.launch("image/*")
            }
        ) {
            Text("Seleccionar de galeria")
        }

        Button(
            onClick = {

                val archivo = File.createTempFile(
                    "foto_perfil_",
                    ".jpg",
                    context.cacheDir
                )

                val uri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.fileprovider",
                    archivo
                )

                imagenCamaraTemporal.value = uri

                camaraLauncher.launch(uri)
            }
        ) {
            Text("Tomar foto")
        }
    }
}