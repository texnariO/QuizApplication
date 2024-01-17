package com.example.quizapplication.presentation.screens.quizscreen.stateandevent

import androidx.navigation.NavController
import androidx.navigation.NavHostController

sealed class QuestionEvent{
    data class NextQuestion(val navController: NavController): QuestionEvent()
    data class ChangeAnswer(val ans: String): QuestionEvent()

    object OpenDialog: QuestionEvent()
    object CloseDialog: QuestionEvent()
}