package com.example.solink.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.solink.network.data.UserResponse
import com.example.solink.ui.theme.SolinkTheme
import com.example.solink.viewmodel.StateHolder

@Composable
fun UserScreen(stateHolder: StateHolder<UserResponse>){
    SolinkTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                when (stateHolder) {
                    is StateHolder.Error -> {
                        Text(text = "Error: ${stateHolder.message}", color = Color.Red)
                    }
                    is StateHolder.Success -> {
                        Text(text = "User: ${stateHolder.data.name}")
                    }
                    is StateHolder.Loading -> {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserScreenLoadingPreview() {
    UserScreen(StateHolder.Loading)
}

@Preview(showBackground = true)
@Composable
fun UserScreenSuccessPreview() {
    UserScreen(StateHolder.Success(UserResponse(id = 0, name = "Josh", username = "", email = "")))
}

@Preview(showBackground = true)
@Composable
fun UserScreenFailurePreview() {
    UserScreen(StateHolder.Error("Unknown Error"))
}