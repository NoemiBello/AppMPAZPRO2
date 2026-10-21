package com.example.appmpazpro2.navigation

sealed class Screen(val route: String) {
    object Inicio : Screen("inicio")
    object Asignaturas : Screen("asignaturas")
    object Progreso : Screen("progreso")
}