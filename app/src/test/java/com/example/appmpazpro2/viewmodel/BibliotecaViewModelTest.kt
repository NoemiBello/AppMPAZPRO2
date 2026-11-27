package com.example.appmpazpro2.viewmodel

import com.example.appmpazpro2.data.model.LibroRecomendado
import com.example.appmpazpro2.repository.BibliotecaRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class BibliotecaViewModelTest : StringSpec({

    val dispatcher = StandardTestDispatcher()

    beforeTest { Dispatchers.setMain(dispatcher) }
    afterTest { Dispatchers.resetMain() }

    val librosMock = listOf(
        LibroRecomendado("/works/1", "Papelucho", "Marcela Paz", 1984, "https://covers.openlibrary.org/b/id/1-M.jpg"),
        LibroRecomendado("/works/2", "Los pecosos", "Marcela Paz", 1980, null)
    )

    "cargarLibrosRecomendados actualiza libros y limpia error" {
        runTest(dispatcher) {
            val repo = mockk<BibliotecaRepository>()
            coEvery { repo.obtenerLibrosRecomendados() } returns librosMock

            val vm = BibliotecaViewModel(repository = repo, cargarAlIniciar = true)
            advanceUntilIdle()

            vm.libros.value shouldContainExactly librosMock
            vm.cargando.value shouldBe false
            vm.error.value.shouldBeNull()
        }
    }

    "cargarLibrosRecomendados maneja excepciones y expone error" {
        runTest(dispatcher) {
            val repo = mockk<BibliotecaRepository>()
            coEvery { repo.obtenerLibrosRecomendados() } throws RuntimeException("Sin internet")

            val vm = BibliotecaViewModel(repository = repo, cargarAlIniciar = false)
            vm.cargarLibrosRecomendados()
            advanceUntilIdle()

            vm.cargando.value shouldBe false
            vm.error.value.orEmpty() shouldContain "Sin internet"
        }
    }

    "buscarLibros busca por consulta y limpiarBusqueda restaura Marcela Paz" {
        runTest(dispatcher) {
            val repo = mockk<BibliotecaRepository>()
            val resultados = listOf(LibroRecomendado("/works/9", "Perico trepa por Chile", "Marcela Paz", 2005, null))
            coEvery { repo.buscarLibros("perico") } returns resultados
            coEvery { repo.obtenerLibrosRecomendados() } returns librosMock

            val vm = BibliotecaViewModel(repository = repo, cargarAlIniciar = false)
            vm.actualizarConsulta("perico")
            vm.consulta.value shouldBe "perico"

            vm.buscarLibros()
            advanceUntilIdle()
            vm.libros.value shouldContainExactly resultados

            vm.limpiarBusqueda()
            advanceUntilIdle()
            vm.consulta.value shouldBe ""
            vm.libros.value shouldContainExactly librosMock
        }
    }

    "buscarLibros con consulta vacia delega a recomendaciones y maneja error" {
        runTest(dispatcher) {
            val repo = mockk<BibliotecaRepository>()
            coEvery { repo.obtenerLibrosRecomendados() } throws IllegalStateException("Timeout")

            val vm = BibliotecaViewModel(repository = repo, cargarAlIniciar = false)
            vm.actualizarConsulta("   ")
            vm.buscarLibros()
            advanceUntilIdle()

            vm.error.value.orEmpty() shouldContain "Timeout"
        }
    }
})
