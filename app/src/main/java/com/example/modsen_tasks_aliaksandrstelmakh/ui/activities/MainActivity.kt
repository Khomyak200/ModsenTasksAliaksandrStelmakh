package com.example.modsen_tasks_aliaksandrstelmakh.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.domain.models.PostDomainModel
import com.example.modsen_tasks_aliaksandrstelmakh.ui.screens.MainScreen
import com.example.modsen_tasks_aliaksandrstelmakh.ui.screens.Task1_1Screen
import com.example.modsen_tasks_aliaksandrstelmakh.ui.screens.Task1_2Screen
import com.example.modsen_tasks_aliaksandrstelmakh.ui.screens.Task2Screen
import com.example.modsen_tasks_aliaksandrstelmakh.ui.screens.Task3_2Screen
import com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels.Task1ViewModel
import com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels.Task2ViewModel
import com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels.Task3_2ViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(navController, startDestination = "start") {
                composable("start") {
                    MainScreen(navController = navController)
                }
                composable("login") {
                    val viewModel: Task1ViewModel = koinViewModel()
                    Task1_1Screen(viewModel) {
                        navController.navigate("empty")
                    }
                }
                composable("empty") {
                    Task1_2Screen()
                }
                composable("posts") {
                    val view2Model: Task2ViewModel = koinViewModel()
                    Task2Screen(view2Model, navController)
                }
                composable("postsSearch") {
                    val viewModel: Task2ViewModel  = koinViewModel()
                    Task2Screen(viewModel, navController)
                }
                composable(
                    "comments/{myObjectJson}"
                ) { backStackEntry ->
                    val myObjectJson = backStackEntry.arguments?.getString("myObjectJson") ?: return@composable
                    val gson = Gson()
                    val post = gson.fromJson(myObjectJson, PostDomainModel::class.java)
                    val viewModel: Task3_2ViewModel = getViewModel { parametersOf(post) }
                    Task3_2Screen(post, viewModel)
                }
            }
        }
    }
}