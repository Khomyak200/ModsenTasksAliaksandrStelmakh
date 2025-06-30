package com.example.modsen_tasks_aliaksandrstelmakh.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.domain.models.CommentDomainModel
import com.example.modsen_tasks_aliaksandrstelmakh.R
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.CommentIntent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.PostIntent

@Composable
fun CommentListBlock(
    comments: List<CommentDomainModel>,
    intent: (CommentIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    if (comments.isNotEmpty()) {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(comments) { comment ->
                CommentItem(
                    comment = comment ,
                    modifier = modifier.fillMaxSize(),
                    onClick = {
                    }
                )
            }
        }
    } else {
        Text(text = "${stringResource(id = R.string.label_comments)}", modifier = Modifier.padding(16.dp))
    }
}