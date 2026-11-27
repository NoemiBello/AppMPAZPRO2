package com.example.appmpazpro2.viewmodel

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class RolViewModelTest : StringSpec({

    "UTP puede gestionar, revisar contenidos y acceder a todas las asignaturas" {
        val vm = RolViewModel()
        vm.rolSeleccionado.value.shouldBeNull()
        vm.puedeAccederAsignatura("Lenguaje") shouldBe false

        vm.seleccionarRol(RolUsuario.UTP)
        vm.puedeGestionarContenido() shouldBe true
        vm.puedeRevisarContenido() shouldBe true
        vm.puedeRevisarRespuestas() shouldBe false
        vm.puedeResponderContenido() shouldBe false
        vm.puedeAccederAsignatura("Matematica") shouldBe true
        vm.puedeAccederAsignatura("Lenguaje") shouldBe true
    }

    "DOCENTE puede gestionar, revisar respuestas y acceder solo a Lenguaje" {
        val vm = RolViewModel()
        vm.seleccionarRol(RolUsuario.DOCENTE)

        vm.puedeGestionarContenido() shouldBe true
        vm.puedeRevisarContenido() shouldBe false
        vm.puedeRevisarRespuestas() shouldBe true
        vm.puedeResponderContenido() shouldBe false
        vm.puedeAccederAsignatura("Lenguaje") shouldBe true
        vm.puedeAccederAsignatura("Historia") shouldBe false
    }

    "ESTUDIANTE puede responder Lenguaje y limpiarRol reinicia el estado" {
        val vm = RolViewModel()
        vm.seleccionarRol(RolUsuario.ESTUDIANTE)

        vm.puedeGestionarContenido() shouldBe false
        vm.puedeResponderContenido() shouldBe true
        vm.puedeAccederAsignatura("lenguaje") shouldBe true
        vm.puedeAccederAsignatura("Ciencias") shouldBe false

        vm.limpiarRol()
        vm.rolSeleccionado.value.shouldBeNull()
    }
})
