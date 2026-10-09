package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
fun HomeScreenMedium() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Text(
            text = "¡Bienvenido!",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Aprende, practica y avanza",
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = "Continúa aprendiendo con MPAZ PRO 2°",
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
                    .height(180.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview(
    name = "Medium",
    widthDp = 700,
    heightDp = 1000,
    showBackground = true
)
@Composable
fun HomeScreenMediumPreview() {
    HomeScreenMedium()
}