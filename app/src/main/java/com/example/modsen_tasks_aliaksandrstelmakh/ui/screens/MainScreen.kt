package com.example.modsen_tasks_aliaksandrstelmakh.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.modsen_tasks_aliaksandrstelmakh.ui.models.ButtonData

@Composable
fun MainScreen(
    navController: NavController,
) {
    val buttons = listOf(
        ButtonData("Task 1") { navController.navigate("login") },
        ButtonData("Task 2") { navController.navigate("posts") }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        buttons.forEach { buttonData ->
            Button(onClick = buttonData.onClick) {
                Text(buttonData.text)
            }
        }
    }
}