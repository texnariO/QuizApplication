package com.example.quizapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapplication.presentation.screens.homescreen.HomeScreen
import com.example.quizapplication.presentation.screens.quizscreen.QuizScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HomeScreen.route){
        composable(Routes.HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(Routes.QuizScreen.route){
            QuizScreen(navController = navController)
        }
        composable(Routes.ResultScreen.route){

        }
        composable(Routes.SettingScreen.route){

        }

    }
}