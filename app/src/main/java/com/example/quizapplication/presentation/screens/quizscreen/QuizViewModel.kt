package com.example.quizapplication.presentation.screens.quizscreen

import androidx.lifecycle.ViewModel
import com.example.quizapplication.domain.repository.QuestionsRepository
import com.example.quizapplication.domain.repository.ScoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val questionsRepository: QuestionsRepository,
    private val scoreRepository: ScoreRepository
): ViewModel(){

}