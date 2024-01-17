package com.example.quizapplication.presentation.screens.quizscreen

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizapplication.R
import com.example.quizapplication.domain.model.Question
import com.example.quizapplication.presentation.screens.quizscreen.stateandevent.QuestionEvent
import com.example.quizapplication.ui.theme.Dimension.BigPadding
import com.example.quizapplication.ui.theme.Dimension.MediumPadding

@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: QuizViewModel = hiltViewModel()
) {
    val currQuestion by viewModel.currQeustionState
    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)
    BackHandler {
        viewModel.onEvent(QuestionEvent.OpenDialog)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                viewModel.onEvent(QuestionEvent.OpenDialog)
            }) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = stringResource(R.string.confirm_pls)
                )
            }
        }
        Spacer(modifier = Modifier.height(BigPadding))
        if (viewModel.dialogState.value.state) {
            AlertDialog(
                onDismissRequest = { viewModel.onEvent(QuestionEvent.CloseDialog) },
                confirmButton = {
                    Button(
                        onClick = {

                            activity?.finish()

                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                        )
                     {
                        Text(
                            text = stringResource(id = R.string.yes),
                            color = Color.White
                        )
                    }
                },
                text = {
                    Text(text = stringResource(id = viewModel.dialogState.value.text))
                })
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (currQuestion.currQuestion?.question != "") {
                QuestionCard(
                    currQuestion.questionNo!!,
                    Question(
                        id = currQuestion.currQuestion?.id!!,
                        correctAnswer = currQuestion.currQuestion?.correctAnswer!!,
                        answers = currQuestion.currQuestion?.answers!!,
                        question = currQuestion.currQuestion?.question!!
                    ),
                    currQuestion.isEnabled,
                    currQuestion.currAnswer,
                    onClick = {
                        viewModel.onEvent(QuestionEvent.ChangeAnswer(it))
                    }
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    onClick = {
                        if (currQuestion.currAnswer != "") {
                            viewModel.onEvent(
                                QuestionEvent.NextQuestion(
                                    navController,
                                )
                            )
                        } else {
                            Toast.makeText(
                                context,
                                context.getString(R.string.confirm_pls),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                ) {
                    Text(
                        text = if (currQuestion.isEnabled) {
                            stringResource(R.string.submit)
                        } else {
                            "Next"
                            stringResource(R.string.next)
                        }
                    )

                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = Color.White
                    )
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun QuestionCard(
    questionNo: Int,
    question: Question,
    isEnabled: Boolean,
    currAns: String,
    onClick: (String) -> Unit
) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(size = 16.dp))
                .background(MaterialTheme.colorScheme.background.copy(0.75f))
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.questionNumber) + "$questionNo. " + question.question,
                fontSize = 16.sp
            )
        }
        LazyColumn(
            contentPadding = PaddingValues(0.dp, 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(question.answers) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            if (currAns == it) {
                                MaterialTheme.colorScheme.error
                            } else {
                                MaterialTheme.colorScheme.secondary
                            }
                        )
                        .clickable(enabled = isEnabled) {
                            onClick(it)
                        }
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp, 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = it, style = MaterialTheme.typography.bodySmall)
                        RadioButton(
                            selected = currAns == it,
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = if (!isEnabled) {
                                    Color.Black
                                } else {
                                    Color.Cyan
                                }
                            )
                        )
                    }
                }
            }
        }

    }
}