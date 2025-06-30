package com.example.modsen_tasks_aliaksandrstelmakh.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.models.CommentDomainModel
import com.example.domain.models.PostDomainModel
import com.example.modsen_tasks_aliaksandrstelmakh.R
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.CommentIntent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.PostIntent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.loaders.InfiniteLoadingIndicator
import com.example.modsen_tasks_aliaksandrstelmakh.ui.state.CommentState
import com.example.modsen_tasks_aliaksandrstelmakh.ui.state.PostState


@Composable
fun ContentComments(
    state: CommentState = CommentState(),
    intent: (CommentIntent) -> Unit = {},
    post: PostDomainModel,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        PostItem(post = post, backgroundColor = Color(0xFF00FF00))
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
            CommentListBlock(comments = state.data ?: emptyList(), intent = intent)
        }
    }
}