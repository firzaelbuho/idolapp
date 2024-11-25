package com.langitbiru.idol.navigation

// Screen.kt
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Kabesha : Screen("kabesha")
    object Transition : Screen("transition/{memberId}") {
        fun createRoute(memberId: Int) = "transition/$memberId"
    }

}
