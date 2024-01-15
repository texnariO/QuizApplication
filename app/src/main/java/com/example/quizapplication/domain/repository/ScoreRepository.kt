package com.example.quizapplication.domain.repository
import kotlinx.coroutines.flow.Flow


interface ScoreRepository {
    suspend fun setPoints(points: Int)
    val getPoints: Flow<Int>
    suspend fun clearPoint()

}