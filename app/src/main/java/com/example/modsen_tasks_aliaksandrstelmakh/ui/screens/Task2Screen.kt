package com.example.modsen_tasks_aliaksandrstelmakh.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.PostIntent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels.Task2ViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Task2Screen(viewModel: Task2ViewModel) {
    val state by viewModel.state.collectAsState()

    val intent: (PostIntent) -> Unit = remember { viewModel::sendIntent }

    Content(state = state, intent = intent)

    LaunchedEffect(Unit) {

    }
}
