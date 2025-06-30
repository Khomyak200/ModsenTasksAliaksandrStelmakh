package com.example.modsen_tasks_aliaksandrstelmakh.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.PostIntent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels.Task2ViewModel

@Composable
fun Task2Screen(viewModel: Task2ViewModel) {
    val state by viewModel.state.collectAsState()

    val intent: (PostIntent) -> Unit = remember { viewModel::sendIntent }

    Content(state = state, intent = intent)

    LaunchedEffect(Unit) {

    }
}
