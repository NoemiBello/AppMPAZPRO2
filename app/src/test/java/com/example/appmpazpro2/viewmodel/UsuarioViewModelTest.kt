package com.example.appmpazpro2.viewmodel

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class UsuarioViewModelTest : StringSpec({

    "validarFormulario detecta errores de campos vacios o invalidos" {
        val vm = UsuarioViewModel()

        vm.validarFormulario() shouldBe false
        vm.uiState.value.errores.nombre shouldBe "El nombre es obligatorio"
        vm.uiState.value.errores.correo shouldBe "Ingrese un correo valido"
        vm.uiState.value.errores.clave shouldBe "La clave debe tener al menos 6 caracteres"
        vm.uiState.value.errores.direccion shouldBe "La direccion es obligatoria"

        vm.actualizarNombre("Jeanpiere")
        vm.actualizarCorreo("sin-arroba")
        vm.actualizarClave("123")
        vm.actualizarDireccion("Av. Marcela Paz 123")
        vm.validarFormulario() shouldBe false
        vm.uiState.value.errores.nombre.shouldBeNull()
        vm.uiState.value.errores.direccion.shouldBeNull()
    }

    "validarFormulario retorna true cuando campos y terminos son validos" {
        val vm = UsuarioViewModel()
        vm.actualizarNombre("Noemi Bello")
        vm.actualizarCorreo("noemi@mpaz.cl")
        vm.actualizarClave("clave123")
        vm.actualizarDireccion("Santiago Centro")
        vm.actualizarTerminos(false)

        vm.validarFormulario() shouldBe false

        vm.actualizarTerminos(true)
        vm.validarFormulario() shouldBe true
        vm.uiState.value.errores.correo.shouldBeNull()
        vm.uiState.value.errores.clave.shouldBeNull()
    }
})
