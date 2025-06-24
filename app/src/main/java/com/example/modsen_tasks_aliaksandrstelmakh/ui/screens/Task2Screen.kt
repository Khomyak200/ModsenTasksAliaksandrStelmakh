package com.example.modsen_tasks_aliaksandrstelmakh.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.PostIntent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels.Task2ViewModel
import com.google.gson.Gson

@Composable
fun Task2Screen(viewModel: Task2ViewModel,   navController: NavController) {
    val state by viewModel.state.collectAsState()

    val intent: (PostIntent) -> Unit = remember { viewModel::sendIntent }

    Content(
        state = state,
        intent = intent,
        onPostClick = { post ->
            try {
                val gson = Gson()
                val myObjectJson = gson.toJson(post)
                navController.navigate("comments/$myObjectJson")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    )

    LaunchedEffect(Unit) {

    }
}
