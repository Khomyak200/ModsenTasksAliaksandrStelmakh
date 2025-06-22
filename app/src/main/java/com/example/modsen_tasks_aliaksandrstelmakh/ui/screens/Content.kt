package com.example.modsen_tasks_aliaksandrstelmakh.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.modsen_tasks_aliaksandrstelmakh.R
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.PostIntent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.loaders.InfiniteLoadingIndicator
import com.example.modsen_tasks_aliaksandrstelmakh.ui.state.PostState

@Composable
@Preview
fun Content(
    state: PostState = PostState(),
    intent: (PostIntent) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TitleBlock(title = stringResource(id = R.string.label_posts))
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                InfiniteLoadingIndicator()
            }
        } else if (state.error != null) {
            Text(
                text = "${stringResource(id = R.string.label_mistake)} ${state.error}",
                modifier = Modifier.padding(16.dp),
                color = Color.Red
            )
        } else {
            PostListBlock(posts = state.data ?: emptyList(), intent = intent)
        }
    }
}