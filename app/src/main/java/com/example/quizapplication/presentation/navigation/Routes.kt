package com.example.quizapplication.presentation.navigation

sealed class Routes(val route: String) {
    object HomeScreen: Routes(route = "home_screen")

    object QuizScreen: Routes(route = "quiz_screen")

    object ResultScreen: Routes(route = "result_screen")

    object SettingScreen: Routes(route = "setting_screen")

}