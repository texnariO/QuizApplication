package com.example.quizapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions_table")
data class Question(
    @PrimaryKey
    val id: String,
    val question: String,
    val answers: List<String>,
    val correctAnswer: String
)