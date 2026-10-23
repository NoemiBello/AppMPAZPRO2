package com.example.appmpazpro2.ui.components

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ImagenInteligente(
    imagenUri: Uri?
) {
    Box(
        modifier = Modifier
            .size(160.dp)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        if (imagenUri == null) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Imagen de perfil",
                modifier = Modifier.size(80.dp),
                tint = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImagenInteligentePreview() {
    ImagenInteligente(
        imagenUri = null
    )
}