package com.example.quizapplication.presentation.screens.resultscreen.stateandevent

import androidx.navigation.NavController

sealed class ResultEvent {
    data class PlayAgain(val navController: NavController): ResultEvent()
    data class GoHomeScreen(val navController: NavController): ResultEvent()
}