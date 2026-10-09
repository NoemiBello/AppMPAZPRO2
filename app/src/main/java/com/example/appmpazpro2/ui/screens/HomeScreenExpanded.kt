package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appmpazpro2.R

@Composable
fun HomeScreenExpanded() {

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalArrangement = Arrangement.spacedBy(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            Text(
                text = "¡Bienvenido!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineLarge
            )

            Text(
                text = "Aprende, practica y avanza",
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = "Explora contenidos y actividades de Segundo Básico.",
                style = MaterialTheme.typography.bodyLarge
            )

            Button(
                onClick = { }
            ) {
                Text("Comenzar")
            }
        }

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo MPAZ PRO 2",
            modifier = Modifier
                .weight(1f)
                .height(300.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(
    name = "Expanded",
    widthDp = 1200,
    heightDp = 800,
    showBackground = true
)
@Composable
fun HomeScreenExpandedPreview() {
    HomeScreenExpanded()
}