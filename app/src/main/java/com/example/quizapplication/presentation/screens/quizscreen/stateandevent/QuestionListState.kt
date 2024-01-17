package com.example.quizapplication.presentation.screens.quizscreen.stateandevent

import com.example.quizapplication.domain.model.Question

data class QuestionListState (
    val list: MutableList<Question> = mutableListOf(),
    val points: Int = 0
)