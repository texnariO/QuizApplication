package com.example.quizapplication.data.data_source.remote

import kotlinx.serialization.Serializable


@Serializable
data class QuestionResponse(
    val id: String,
    val question: String,
    val answers: List<String>,
    val correctAnswer: String
)