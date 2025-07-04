package com.example.modsen_tasks_aliaksandrstelmakh.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.models.PostDomainModel
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.PostIntent

@Composable
fun PostListBlockSimple(
    posts: List<PostDomainModel>,
    intent: (PostIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    if (posts.isNotEmpty()) {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(posts) { post ->
                PostItem(
                    post = post,
                    modifier = modifier.fillMaxSize(),
                    onClick = { }
                )
            }
        }
    } else {
        Text("Нет постов", modifier = Modifier.padding(16.dp))
    }
}