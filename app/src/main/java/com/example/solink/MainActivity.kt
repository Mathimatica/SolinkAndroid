package com.example.solink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import com.example.solink.ui.theme.SolinkTheme
import com.example.solink.viewmodel.UserViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.example.solink.ui.screen.HomeScreen
import com.example.solink.ui.screen.UserScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SLNav()
        }
    }
}

@Serializable
object HomeScreenArgs

@Serializable
class UserScreenArgs(val userId:Int)

@Composable
fun SLNav(){
    SolinkTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = HomeScreenArgs) {
            composable<HomeScreenArgs> { HomeScreen {
                val randomNumber = (1..10).random()
                navController.navigate(UserScreenArgs(randomNumber))
            } }
            composable<UserScreenArgs> {
                val userViewModel = hiltViewModel<UserViewModel>()
                val stateHolder = userViewModel.stateHolder.value
                UserScreen(stateHolder)
            }
        }
    }
}