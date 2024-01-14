package com.example.quizapplication.domain.repository
import com.example.quizapplication.domain.model.Question
import kotlinx.coroutines.flow.Flow

interface QuestionsRepository {
    suspend fun getQuestions(): Flow<List<Question>>
    suspend fun clearQuestions()
}