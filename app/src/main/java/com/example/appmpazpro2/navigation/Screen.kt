package com.example.appmpazpro2.navigation

sealed class Screen(
    val route: String
) {

    object Inicio :
        Screen("inicio")

    object Asignaturas :
        Screen("asignaturas")

    object UnidadObjetivo :
        Screen("unidad_objetivo")

    object Progreso :
        Screen("progreso")
}