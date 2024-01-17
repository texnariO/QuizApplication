package com.example.quizapplication.presentation.screens.resultscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapplication.domain.repository.ScoreRepository
import com.example.quizapplication.presentation.navigation.Routes
import com.example.quizapplication.presentation.screens.resultscreen.stateandevent.ResultEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val scoreRepository: ScoreRepository
) : ViewModel() {
    private val _points = mutableIntStateOf(0)
    val points: State<Int> = _points

    init {
        viewModelScope.launch {
            scoreRepository.getPoints.collect {
                _points.intValue = it
            }
        }
    }
    fun onEvent(event: ResultEvent){
        when(event){
            is ResultEvent.PlayAgain -> {
                event.navController.navigate(Routes.QuizScreen.route) {
                    popUpTo(Routes.HomeScreen.route) {
                        inclusive = false
                    }
                }
                viewModelScope.launch {
                    scoreRepository.clearPoint()
                }
            }
            is ResultEvent.GoHomeScreen -> {
                event.navController.navigate(Routes.HomeScreen.route) {
                    popUpTo(Routes.HomeScreen.route) {
                        inclusive = false
                    }
                }
                viewModelScope.launch {
                    scoreRepository.clearPoint()
                }
            }
        }
    }
}