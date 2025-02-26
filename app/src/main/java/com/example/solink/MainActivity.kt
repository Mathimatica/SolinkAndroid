package com.example.solink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.solink.ui.theme.SolinkTheme
import com.example.solink.viewmodel.UserViewModel
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
class UserScreenArgs(val userId:Int, val pageNum:Int, val pagePer:Int)

@Composable
fun SLNav(){
    SolinkTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = HomeScreenArgs) {
            composable<HomeScreenArgs> { HomeScreen {
                val randomNumber = (1..10).random()
                navController.navigate(UserScreenArgs(randomNumber, randomNumber, randomNumber))
            } }
            composable<UserScreenArgs> {
                val userViewModel = hiltViewModel<UserViewModel>()
                val stateHolder = userViewModel.stateHolder.value
                UserScreen(stateHolder){
                    navController.popBackStack()
                }
            }
        }
    }
}