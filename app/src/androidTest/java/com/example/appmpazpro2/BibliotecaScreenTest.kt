package com.example.appmpazpro2

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.appmpazpro2.data.model.LibroRecomendado
import com.example.appmpazpro2.ui.screens.BibliotecaScreen
import com.example.appmpazpro2.viewmodel.BibliotecaViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Rule
import org.junit.Test

class BibliotecaScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun los_libros_recomendados_se_muestran_en_pantalla() {
        val fakeLibros = listOf(
            LibroRecomendado("/works/1", "Papelucho", "Marcela Paz", 1984, null),
            LibroRecomendado("/works/2", "Perico trepa por Chile", "Marcela Paz", 2005, null)
        )

        val fakeViewModel = object : BibliotecaViewModel(cargarAlIniciar = false) {
            override val libros: StateFlow<List<LibroRecomendado>> = MutableStateFlow(fakeLibros)
            override val cargando: StateFlow<Boolean> = MutableStateFlow(false)
            override val error: StateFlow<String?> = MutableStateFlow(null)
        }

        composeRule.setContent {
            BibliotecaScreen(viewModel = fakeViewModel)
        }

        composeRule.onNodeWithText("Biblioteca recomendada").assertIsDisplayed()
        composeRule.onNodeWithText("Papelucho").assertIsDisplayed()
        composeRule.onNodeWithText("Perico trepa por Chile").assertIsDisplayed()
    }
}
