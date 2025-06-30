package com.example.modsen_tasks_aliaksandrstelmakh.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.domain.models.PostDomainModel
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.CommentIntent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.PostIntent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels.Task3_2ViewModel

@Composable
fun Task3_2Screen(post: PostDomainModel, viewModel: Task3_2ViewModel) {
    val state by viewModel.state.collectAsState()
    val intent: (CommentIntent) -> Unit = remember { viewModel::sendIntent }

    ContentComments(state = state, intent = intent, post = post)

    LaunchedEffect(Unit) {

    }
}