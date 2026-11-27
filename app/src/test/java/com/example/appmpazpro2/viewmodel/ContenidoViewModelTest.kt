package com.example.appmpazpro2.viewmodel

import android.app.Application
import com.example.appmpazpro2.data.ContenidoLocalStorage
import com.example.appmpazpro2.model.ContenidoUiState
import com.example.appmpazpro2.model.EstadoContenido
import com.example.appmpazpro2.repository.ContenidoRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.mockk.coEvery
import io.mockk.coVerify
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
class ContenidoViewModelTest : StringSpec({

    val dispatcher = StandardTestDispatcher()
    val app = mockk<Application>(relaxed = true)

    beforeTest { Dispatchers.setMain(dispatcher) }
    afterTest { Dispatchers.resetMain() }

    val aprobadoLenguaje = ContenidoUiState(
        id = 1,
        asignatura = "Lenguaje",
        unidad = "Unidad 1",
        objetivo = "OA 5",
        tipo = "Lectura",
        titulo = "Papelucho",
        contenido = "Lee el cuento",
        pregunta = "Que paso?",
        imagenUri = "content://img/1",
        estado = EstadoContenido.APROBADO
    )

    val pendienteMatematica = ContenidoUiState(
        id = 2,
        asignatura = "Matematica",
        unidad = "Unidad 2",
        objetivo = "OA 9",
        tipo = "Actividad",
        titulo = "Sumas",
        contenido = "Resuelve 5 + 5",
        pregunta = "Cuanto es?",
        estado = EstadoContenido.PENDIENTE
    )

    "sincronizarConBackend obtiene contenidos remotos y los respalda en DataStore" {
        runTest(dispatcher) {
            val repo = mockk<ContenidoRepository>()
            val storage = mockk<ContenidoLocalStorage>(relaxed = true)
            every { storage.obtenerContenidos() } returns flowOf(emptyList())
            coEvery { repo.obtenerContenidos() } returns listOf(aprobadoLenguaje, pendienteMatematica)

            val vm = ContenidoViewModel(app, repo, storage, cargarAlIniciar = true)
            advanceUntilIdle()

            vm.contenidos.value shouldHaveSize 2
            vm.sincronizando.value shouldBe false
            vm.mensajeSincronizacion.value.orEmpty() shouldContain "Sincronizado"
            coVerify { storage.guardarContenidos(any()) }
        }
    }

    "cargarContenidosIniciales mantiene respaldo local cuando falla el backend" {
        runTest(dispatcher) {
            val repo = mockk<ContenidoRepository>()
            val storage = mockk<ContenidoLocalStorage>(relaxed = true)
            every { storage.obtenerContenidos() } returns flowOf(listOf(aprobadoLenguaje))
            coEvery { repo.obtenerContenidos() } throws RuntimeException("Connection refused")

            val vm = ContenidoViewModel(app, repo, storage, cargarAlIniciar = true)
            advanceUntilIdle()

            vm.contenidos.value shouldHaveSize 1
            vm.mensajeSincronizacion.value.orEmpty() shouldContain "respaldo local"
        }
    }

    "validarFormulario detecta todos los campos vacios y acepta formulario valido" {
        val repo = mockk<ContenidoRepository>(relaxed = true)
        val storage = mockk<ContenidoLocalStorage>(relaxed = true)
        val vm = ContenidoViewModel(app, repo, storage, cargarAlIniciar = false)

        vm.validarFormulario() shouldBe false
        vm.errores.value.asignatura shouldBe "La asignatura es obligatoria"
        vm.errores.value.unidad shouldBe "La unidad es obligatoria"
        vm.errores.value.objetivo shouldBe "El objetivo es obligatorio"
        vm.errores.value.tipo shouldBe "El tipo es obligatorio"
        vm.errores.value.titulo shouldBe "El titulo es obligatorio"
        vm.errores.value.contenido shouldBe "El contenido es obligatorio"
        vm.errores.value.pregunta shouldBe "La pregunta es obligatoria"

        vm.actualizarAsignatura("Lenguaje")
        vm.actualizarUnidad("Unidad 1")
        vm.actualizarObjetivo("OA 5")
        vm.actualizarTipo("Lectura")
        vm.actualizarTitulo("Papelucho")
        vm.actualizarContenido("Texto")
        vm.actualizarPregunta("Pregunta?")
        vm.actualizarImagen("content://foto/1")

        vm.validarFormulario() shouldBe true
        vm.errores.value.pregunta.shouldBeNull()
        vm.uiState.value.imagenUri shouldBe "content://foto/1"
    }

    "guardarContenido aplica reglas de negocio por rol y envia POST al backend" {
        runTest(dispatcher) {
            val repo = mockk<ContenidoRepository>()
            val storage = mockk<ContenidoLocalStorage>(relaxed = true)
            coEvery { repo.crearContenido(any()) } returns aprobadoLenguaje.copy(id = 50, estado = EstadoContenido.PENDIENTE)

            val vm = ContenidoViewModel(app, repo, storage, cargarAlIniciar = false)

            // Estudiante no puede guardar
            vm.guardarContenido(RolUsuario.ESTUDIANTE) shouldBe false

            // Docente solo puede Lenguaje
            vm.prepararNuevoContenido("Matematica", "Unidad 1", "OA 1")
            vm.actualizarTipo("Actividad")
            vm.actualizarTitulo("Sumas")
            vm.actualizarContenido("2+2")
            vm.actualizarPregunta("Cuanto es?")
            vm.guardarContenido(RolUsuario.DOCENTE) shouldBe false
            vm.errores.value.asignatura shouldBe "El docente solo puede trabajar con Lenguaje"

            // Docente con Lenguaje -> PENDIENTE y POST
            vm.actualizarAsignatura("Lenguaje")
            vm.guardarContenido(RolUsuario.DOCENTE) shouldBe true
            advanceUntilIdle()

            vm.contenidos.value shouldHaveSize 1
            vm.contenidos.value[0].id shouldBe 50
            vm.contenidos.value[0].estado shouldBe EstadoContenido.PENDIENTE

            // UTP guarda directamente APROBADO
            coEvery { repo.crearContenido(any()) } throws RuntimeException("offline")
            vm.prepararNuevoContenido("Matematica", "Unidad 1", "OA 2")
            vm.actualizarTipo("Evaluacion")
            vm.actualizarTitulo("Restas")
            vm.actualizarContenido("5-2")
            vm.actualizarPregunta("Resultado?")
            vm.guardarContenido(RolUsuario.UTP) shouldBe true
            advanceUntilIdle()

            vm.contenidos.value shouldHaveSize 2
            vm.contenidos.value.last().estado shouldBe EstadoContenido.APROBADO
            vm.mensajeSincronizacion.value.orEmpty() shouldContain "localmente"
        }
    }

    "editarContenido aplica reglas de rol y envia PUT al backend" {
        runTest(dispatcher) {
            val repo = mockk<ContenidoRepository>()
            val storage = mockk<ContenidoLocalStorage>(relaxed = true)
            every { storage.obtenerContenidos() } returns flowOf(emptyList())
            coEvery { repo.obtenerContenidos() } returns listOf(aprobadoLenguaje)
            coEvery { repo.actualizarContenido(1, any()) } returns aprobadoLenguaje.copy(
                titulo = "Papelucho Editado",
                estado = EstadoContenido.PENDIENTE
            )

            val vm = ContenidoViewModel(app, repo, storage, cargarAlIniciar = true)
            advanceUntilIdle()

            vm.editarContenido(RolUsuario.ESTUDIANTE) shouldBe false

            vm.cargarContenidoParaEditar(aprobadoLenguaje)
            vm.actualizarAsignatura("Historia")
            vm.editarContenido(RolUsuario.DOCENTE) shouldBe false

            vm.actualizarAsignatura("Lenguaje")
            vm.actualizarTitulo("Papelucho Editado")
            vm.editarContenido(RolUsuario.DOCENTE) shouldBe true
            advanceUntilIdle()

            vm.contenidos.value[0].titulo shouldBe "Papelucho Editado"
            vm.contenidos.value[0].estado shouldBe EstadoContenido.PENDIENTE
            vm.mensajeSincronizacion.value.orEmpty() shouldContain "PUT"
        }
    }

    "aprobar, rechazar, ocultar, reactivar y eliminar respetan rol UTP y llaman al backend" {
        runTest(dispatcher) {
            val repo = mockk<ContenidoRepository>()
            val storage = mockk<ContenidoLocalStorage>(relaxed = true)
            every { storage.obtenerContenidos() } returns flowOf(emptyList())
            coEvery { repo.obtenerContenidos() } returns listOf(pendienteMatematica)
            coEvery { repo.actualizarContenido(any(), any()) } answers { secondArg() }
            coEvery { repo.eliminarContenido(2) } returns true

            val vm = ContenidoViewModel(app, repo, storage, cargarAlIniciar = true)
            advanceUntilIdle()

            vm.aprobarContenido(2, RolUsuario.DOCENTE) shouldBe false
            vm.rechazarContenido(2, RolUsuario.DOCENTE) shouldBe false
            vm.ocultarContenido(2, RolUsuario.DOCENTE) shouldBe false

            vm.aprobarContenido(2, RolUsuario.UTP) shouldBe true
            advanceUntilIdle()
            vm.contenidosAprobados() shouldHaveSize 1

            vm.rechazarContenido(2, RolUsuario.UTP) shouldBe true
            advanceUntilIdle()
            vm.contenidosRechazados() shouldHaveSize 1

            vm.ocultarContenido(2, RolUsuario.UTP) shouldBe true
            advanceUntilIdle()
            vm.contenidosOcultos() shouldHaveSize 1

            vm.reactivarContenido(2, RolUsuario.DOCENTE)
            vm.contenidosOcultos() shouldHaveSize 1

            vm.reactivarContenido(2, RolUsuario.UTP)
            advanceUntilIdle()
            vm.contenidosAprobados() shouldHaveSize 1

            vm.contenidoPorId(2)?.id shouldBe 2
            vm.puedeEditarContenido(aprobadoLenguaje, RolUsuario.UTP) shouldBe true
            vm.puedeEditarContenido(aprobadoLenguaje, RolUsuario.DOCENTE) shouldBe true
            vm.puedeEditarContenido(pendienteMatematica, RolUsuario.DOCENTE) shouldBe false
            vm.puedeEditarContenido(aprobadoLenguaje, RolUsuario.ESTUDIANTE) shouldBe false

            vm.eliminarContenido(2, RolUsuario.DOCENTE)
            vm.contenidos.value shouldHaveSize 1

            vm.eliminarContenido(2, RolUsuario.UTP)
            advanceUntilIdle()
            vm.contenidos.value shouldHaveSize 0
            vm.mensajeSincronizacion.value.orEmpty() shouldContain "DELETE"
        }
    }

    "contenidosVisibles filtra por rol, asignatura, unidad y objetivo" {
        runTest(dispatcher) {
            val repo = mockk<ContenidoRepository>()
            val storage = mockk<ContenidoLocalStorage>(relaxed = true)
            every { storage.obtenerContenidos() } returns flowOf(emptyList())
            coEvery { repo.obtenerContenidos() } returns listOf(aprobadoLenguaje, pendienteMatematica)

            val vm = ContenidoViewModel(app, repo, storage, cargarAlIniciar = true)
            advanceUntilIdle()

            vm.contenidosVisibles(RolUsuario.ESTUDIANTE) shouldHaveSize 1
            vm.contenidosVisibles(RolUsuario.UTP) shouldHaveSize 2
            vm.contenidosVisibles(RolUsuario.DOCENTE, asignatura = "Lenguaje", unidad = "Unidad 1", objetivo = "OA 5") shouldHaveSize 1
            vm.contenidosVisibles(null) shouldHaveSize 0
            vm.contenidosPendientes() shouldHaveSize 1
        }
    }
})
