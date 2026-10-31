package com.example.appmpazpro2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appmpazpro2.navigation.Screen
import com.example.appmpazpro2.ui.theme.AmarilloClaroMPaz
import com.example.appmpazpro2.ui.theme.AzulClaroMPaz
import com.example.appmpazpro2.ui.theme.VerdeClaroMPaz
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    navController: NavHostController
) {

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {

                Text(
                    text = "MPAZ PRO 2",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )

                NavigationDrawerItem(
                    label = {
                        Text("Inicio")
                    },
                    selected = true,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }

                        navController.navigate(Screen.Inicio.route)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Inicio"
                        )
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text("Asignaturas")
                    },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }

                        navController.navigate(Screen.Asignaturas.route)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = "Asignaturas"
                        )
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text("Progreso")
                    },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }

                        navController.navigate(Screen.Progreso.route)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.ShowChart,
                            contentDescription = "Progreso"
                        )
                    }
                )
            }
        }
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "MPAZ PRO 2",
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Abrir menú"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = "¡Bienvenido! 👋",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Aprende, practica y avanza",
                    style = MaterialTheme.typography.titleMedium
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = AmarilloClaroMPaz
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "📖",
                            style = MaterialTheme.typography.headlineLarge
                        )

                        Text(
                            text = "MPAZ PRO 2",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Text(
                            text = "2° Básico",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(top = 6.dp)
                        )

                        Text(
                            text = "¡Tu espacio para aprender jugando!",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    }
                }

                Text(
                    text = "¿Qué quieres hacer?",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = AzulClaroMPaz
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        Text(
                            text = "📚",
                            style = MaterialTheme.typography.headlineLarge
                        )

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = "Mis asignaturas",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Revisa tus unidades y actividades",
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Button(
                                onClick = {
                                    navController.navigate(
                                        Screen.Asignaturas.route
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp)
                            ) {
                                Text("Ver asignaturas")
                            }
                        }
                    }
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = VerdeClaroMPaz
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        Text(
                            text = "⭐",
                            style = MaterialTheme.typography.headlineLarge
                        )

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = "Mi progreso",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Revisa cuánto has avanzado",
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Button(
                                onClick = {
                                    navController.navigate(
                                        Screen.Progreso.route
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp)
                            ) {
                                Text("Ver progreso")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InicioScreenPreview() {

    val navController = rememberNavController()

    InicioScreen(
        navController = navController
    )
}