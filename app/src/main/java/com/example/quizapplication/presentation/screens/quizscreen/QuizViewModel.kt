package com.example.quizapplication.presentation.screens.quizscreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapplication.domain.repository.QuestionsRepository
import com.example.quizapplication.domain.repository.ScoreRepository
import com.example.quizapplication.presentation.navigation.Routes
import com.example.quizapplication.presentation.screens.quizscreen.stateandevent.DialogState
import com.example.quizapplication.presentation.screens.quizscreen.stateandevent.QuestionEvent
import com.example.quizapplication.presentation.screens.quizscreen.stateandevent.QuestionListState
import com.example.quizapplication.presentation.screens.quizscreen.stateandevent.QuestionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val questionsRepository: QuestionsRepository,
    private val scoreRepository: ScoreRepository
) : ViewModel() {
    private val _currQuestionState = mutableStateOf(QuestionState())
    val currQuestionState: State<QuestionState> = _currQuestionState

    private val _questionsList = mutableStateOf(QuestionListState())
    val questionsList: State<QuestionListState> = _questionsList

    init {
        viewModelScope.launch {
            questionsRepository.getQuestions().collect {
                _questionsList.value = questionsList.value.copy(
                    list = it.toMutableList()
                )
                Log.d("Quiz", currQuestionState.value.currQuestion!!.question)
                _currQuestionState.value = currQuestionState.value.copy(
                    questionNo = 1,
                    currQuestion = it[0]
                )
            }
            scoreRepository.getPoints.collect {
                _questionsList.value = _questionsList.value.copy(
                    points = it,
                )
            }
        }
    }

    private val _dialogState = mutableStateOf(DialogState())
    val dialogState: State<DialogState> = _dialogState

    fun onEvent(event: QuestionEvent) {
        when (event) {
            is QuestionEvent.NextQuestion -> {
                    if(_currQuestionState.value.currQuestion?.correctAnswer == _currQuestionState.value.currAnswer){
                        _currQuestionState.value = currQuestionState.value.copy(
                            isEnabled = true,
                            currAnswer = ""
                        )
                        _questionsList.value = questionsList.value.copy(
                            points = questionsList.value.points+1
                        )
                        if(_currQuestionState.value.questionNo != _questionsList.value.list.size) {
                            _currQuestionState.value = currQuestionState.value.copy(

                                questionNo = _currQuestionState.value.questionNo!! + 1,
                                currQuestion = _questionsList.value.list[_currQuestionState.value.questionNo!!]
                            )
                        }else{
                            viewModelScope.launch{
                                scoreRepository.setPoints(_questionsList.value.points)
                            }
                            event.navController.navigate(Routes.ResultScreen.route){
                                popUpTo(Routes.HomeScreen.route){
                                    inclusive = false
                                }
                            }
                        }
                    }
                    else{
                        _currQuestionState.value = currQuestionState.value.copy(
                            isEnabled = true,
                            currAnswer = ""
                        )
                        if(_currQuestionState.value.questionNo != _questionsList.value.list.size) {
                            _currQuestionState.value = currQuestionState.value.copy(
                                questionNo = _currQuestionState.value.questionNo!! + 1,
                                currQuestion = _questionsList.value.list[_currQuestionState.value.questionNo!!]
                            )
                        }else{
                            viewModelScope.launch{
                                scoreRepository.setPoints(_questionsList.value.points)
                            }
                            event.navController.navigate(Routes.ResultScreen.route){
                                popUpTo(Routes.HomeScreen.route){
                                    inclusive = false
                                }
                            }
                        }
                    }
            }
            is QuestionEvent.ChangeAnswer -> {
                _currQuestionState.value = currQuestionState.value.copy(
                    currAnswer = event.ans
                )
            }
            is QuestionEvent.OpenDialog -> {
                _dialogState.value = dialogState.value.copy(
                    state = true
                )
            }
            is QuestionEvent.CloseDialog -> {
                _dialogState.value = dialogState.value.copy(
                    state = false
                )
            }
        }
    }

}