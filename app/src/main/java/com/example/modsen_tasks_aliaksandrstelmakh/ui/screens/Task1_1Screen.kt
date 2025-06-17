package com.example.modsen_tasks_aliaksandrstelmakh.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.modsen_tasks_aliaksandrstelmakh.ui.intent.LoginIntent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels.LoginEvent
import com.example.modsen_tasks_aliaksandrstelmakh.ui.viewmodels.Task1ViewModel
import kotlinx.coroutines.flow.filterIsInstance

@Composable
fun Task1_1Screen(
    viewModel: Task1ViewModel,
    onNavigateToNextScreen: () -> Unit
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    val eventMain by remember { mutableStateOf(viewModel.event) }

    LaunchedEffect(Unit) {
        eventMain
            .filterIsInstance<LoginEvent.ShowError>()
            .collect { event ->
                Toast.makeText(context, event.error, Toast.LENGTH_SHORT).show()
            }
    }

    LaunchedEffect(Unit) {
        eventMain
            .filterIsInstance<LoginEvent.NavigateToSuccessScreen>()
            .collect {
                onNavigateToNextScreen()
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = state.login,
            onValueChange = { viewModel.processIntent(LoginIntent.UpdateLogin(it)) },
            label = { Text("Логин") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = state.password,
            onValueChange = { viewModel.processIntent(LoginIntent.UpdatePassword(it)) },
            label = { Text("Пароль") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.processIntent(LoginIntent.Submit) },
            enabled = state.isButtonEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Вход")
        }
    }
}