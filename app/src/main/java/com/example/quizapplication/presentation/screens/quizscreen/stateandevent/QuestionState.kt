package com.example.quizapplication.presentation.screens.quizscreen.stateandevent

import com.example.quizapplication.domain.model.Question

data class QuestionState(
    val questionNo: Int? = 0,
    val currQuestion: Question? = Question(
        id = "",
        question = "",
        answers = listOf(),
        correctAnswer = ""
    ),
    val isEnabled: Boolean = true,
    val currAnswer: String =""
)