package com.example.modsen_tasks_aliaksandrstelmakh.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.domain.models.CommentDomainModel
import com.example.domain.models.PostDomainModel
import com.example.modsen_tasks_aliaksandrstelmakh.R

@Composable
fun CommentItem(
    comment: CommentDomainModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${stringResource(id = R.string.label_comments_postId)} ${comment.postId}", style = MaterialTheme.typography.caption)
            Text(text = "${stringResource(id = R.string.label_comments_id)} ${comment.id}", style = MaterialTheme.typography.caption)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = comment.name, style = MaterialTheme.typography.subtitle1)
            Text(text = comment.email, style = MaterialTheme.typography.subtitle1)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = comment.body, style = MaterialTheme.typography.body2)
        }
    }
}