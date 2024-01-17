package com.example.quizapplication.presentation.screens.resultscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.quizapplication.R
import com.example.quizapplication.presentation.screens.resultscreen.stateandevent.ResultEvent
import com.example.quizapplication.ui.theme.Dimension.BigPadding
import com.example.quizapplication.ui.theme.Dimension.MediumPadding


@Composable
fun ResultScreen(
    navController: NavController,
    viewModel: ResultViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val context = LocalContext.current
        Text(text = stringResource(id = R.string.game_over), style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(BigPadding))
        Text(text = viewModel.poins.value.toString(), style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(BigPadding))
        Row {
            Column(modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    viewModel.onEvent(ResultEvent.PlayAgain(navController))
                }
                .background(MaterialTheme.colorScheme.secondary.copy(0.5f))
                .padding(16.dp)
            ) {
                Icon(imageVector = Icons.Outlined.Refresh, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(MediumPadding))
            Column(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        viewModel.onEvent(ResultEvent.GoHomeScreen(navController))
                    }
                    .background(MaterialTheme.colorScheme.error.copy(0.5f))
                    .padding(16.dp)
            ) {

                Icon(imageVector = Icons.Outlined.Home, contentDescription = null)

            }
        }
    }
}