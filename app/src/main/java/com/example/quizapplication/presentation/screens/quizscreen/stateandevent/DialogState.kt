package com.example.quizapplication.presentation.screens.quizscreen.stateandevent

import androidx.compose.ui.res.stringResource
import com.example.quizapplication.R

data class DialogState (
    val state: Boolean = false,
    val text: Int = R.string.exit
)