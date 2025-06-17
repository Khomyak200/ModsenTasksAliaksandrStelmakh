package com.example.modsen_tasks_aliaksandrstelmakh.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modsen_tasks_aliaksandrstelmakh.ui.screens.MainScreen
import com.example.modsen_tasks_aliaksandrstelmakh.ui.screens.Task1_1Screen
import com.example.modsen_tasks_aliaksandrstelmakh.ui.screens.Task1_2Screen
import com.example.modsen_tasks_aliaksandrstelmakh.ui.theme.ModsenTasksAliaksandrStelmakhTheme
import com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels.Task1ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(navController, startDestination = "start") {
                composable("start") {
                    MainScreen {
                        navController.navigate("login")
                    }
                }
                composable("login") {
                    val viewModel: Task1ViewModel by viewModel()
                    Task1_1Screen(viewModel) {
                        navController.navigate("empty")
                    }
                }
                composable("empty") {
                    Task1_2Screen()
                }
            }
        }
    }
}