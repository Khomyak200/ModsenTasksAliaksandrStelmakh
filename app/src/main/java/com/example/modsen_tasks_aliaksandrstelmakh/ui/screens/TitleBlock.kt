package com.example.modsen_tasks_aliaksandrstelmakh.ui.screens
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TitleBlock(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        style = MaterialTheme.typography.h4,
        modifier = modifier.padding(16.dp)
    )
}

@Preview
@Composable
private fun PreviewTitleBlock() {
    TitleBlock(title = "Заголовок экрана")
}