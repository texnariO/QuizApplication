package com.example.quizapplication.data.repository

import com.example.quizapplication.data.data_source.local.QuestionCacheDao
import com.example.quizapplication.data.data_source.remote.ApiQuiz
import com.example.quizapplication.domain.model.Question
import com.example.quizapplication.domain.repository.QuestionsRepository
import kotlinx.coroutines.flow.Flow

class QuestionsRepositoryImpl(
    private val dao: QuestionCacheDao,
    private val apiService: ApiQuiz
): QuestionsRepository {
    override suspend fun getQuestions(): Flow<List<Question>> {
        apiService.getQuestions().forEach{
            dao.insertQuestion(
                Question(
                    id = it.id,
                    question =  it.question,
                    answers =  it.answers.shuffled(),
                    correctAnswer = it.correctAnswer
                )
            )
        }
        return dao.getQuestions()
    }

    override suspend fun clearQuestions() {
        dao.clear()
    }
}