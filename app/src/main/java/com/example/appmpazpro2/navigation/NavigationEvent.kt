package com.example.appmpazpro2.navigation

sealed class NavigationEvent {
    data class NavigateTo(val route: String) : NavigationEvent()
    object NavigateBack : NavigationEvent()
}