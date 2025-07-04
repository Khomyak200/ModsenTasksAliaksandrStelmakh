package com.example.modsen_tasks_aliaksandrstelmakh.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.models.PostDomainModel
import com.example.modsen_tasks_aliaksandrstelmakh.R
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.PostIntent

@Composable
fun PostListBlock(
    posts: List<PostDomainModel>,
    onPostClick: (PostDomainModel) -> Unit,
    intent: (PostIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    if (posts.isNotEmpty()) {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(posts) { post ->
                PostItem(
                    post = post,
                    modifier = modifier.fillMaxSize(),
                    onClick = { onPostClick(post) }
                )
            }
        }
    } else {
        Text("${stringResource(id = R.string.label_no_posts)}", modifier = Modifier.padding(16.dp))
    }
}


