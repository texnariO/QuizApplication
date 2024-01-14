package com.example.quizapplication.presentation.screens.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.quizapplication.R
import com.example.quizapplication.presentation.navigation.Routes
import com.example.quizapplication.ui.theme.Dimension.BigPadding

@Composable
fun HomeScreen(navController: NavController) {
    Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.fillMaxSize()) {
        IconButton(onClick = { /* navigate to settings */ }) {
            Icon(Icons.Outlined.Settings, contentDescription = "Settings description")
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(BigPadding),
            text = stringResource(id = R.string.welcomeMessage),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(BigPadding))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            onClick = { navController.navigate(Routes.QuizScreen.route){

            } }) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = stringResource(id = R.string.startQuiz),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}