package com.example.appmpazpro2.viewmodel

import android.app.Application
import com.example.appmpazpro2.data.ContenidoLocalStorage
import com.example.appmpazpro2.model.EstadoRespuesta
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class ProgresoViewModelTest : StringSpec({

    val dispatcher = StandardTestDispatcher()
    val app = mockk<Application>(relaxed = true)

    beforeTest { Dispatchers.setMain(dispatcher) }
    afterTest { Dispatchers.resetMain() }

    "registrarRespuesta valida texto vacio y limite maximo de 3 intentos" {
        runTest(dispatcher) {
            val storage = mockk<ContenidoLocalStorage>(relaxed = true)
            every { storage.obtenerRespuestas() } returns flowOf(emptyList())

            val vm = ProgresoViewModel(app, storage, cargarAlIniciar = true)
            advanceUntilIdle()

            vm.registrarRespuesta(1) shouldBe false
            vm.intentosRestantes(1) shouldBe 3
            vm.puedeResponder(1) shouldBe true

            repeat(3) { i ->
                vm.actualizarRespuesta("Respuesta $i")
                vm.respuestaActual.value shouldBe "Respuesta $i"
                vm.registrarRespuesta(1) shouldBe true
            }

            vm.intentosUsados(1) shouldBe 3
            vm.intentosRestantes(1) shouldBe 0
            vm.puedeResponder(1) shouldBe false

            vm.actualizarRespuesta("Intento 4 bloqueado")
            vm.registrarRespuesta(1) shouldBe false
            vm.respuestasDeContenido(1) shouldHaveSize 3
            vm.respuestasPendientes() shouldHaveSize 3
            vm.obtenerRespuestasPendientes() shouldHaveSize 3
        }
    }

    "marcarCorrecta y marcarIncorrecta validan rol y recalculan porcentaje de progreso" {
        runTest(dispatcher) {
            val storage = mockk<ContenidoLocalStorage>(relaxed = true)
            val vm = ProgresoViewModel(app, storage, cargarAlIniciar = false)

            vm.actualizarRespuesta("R1")
            vm.registrarRespuesta(10) shouldBe true
            vm.actualizarRespuesta("R2")
            vm.registrarRespuesta(10) shouldBe true

            vm.marcarCorrecta(1, RolUsuario.ESTUDIANTE) shouldBe false
            vm.marcarIncorrecta(2, RolUsuario.ESTUDIANTE) shouldBe false

            vm.marcarCorrecta(1, RolUsuario.DOCENTE) shouldBe true
            vm.marcarIncorrecta(2, RolUsuario.UTP) shouldBe true
            advanceUntilIdle()

            vm.respuestas.value[0].estado shouldBe EstadoRespuesta.CORRECTA
            vm.respuestas.value[1].estado shouldBe EstadoRespuesta.INCORRECTA
            vm.porcentaje.value shouldBe 50
            vm.progreso.value shouldBe 0.5f
        }
    }
})
