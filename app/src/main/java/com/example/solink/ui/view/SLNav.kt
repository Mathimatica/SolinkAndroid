package com.example.solink.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.solink.UserListScreenArgs
import com.example.solink.UserScreenArgs
import com.example.solink.ui.screen.UserListScreen
import com.example.solink.ui.screen.UserScreen
import com.example.solink.ui.stateholder.UserStateHolder
import com.example.solink.ui.theme.SolinkTheme

@Composable
fun SLNav(){
    SolinkTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = UserListScreenArgs((1..10).random(), 50)) {
            composable<UserListScreenArgs> {
                UserListScreen{
                    navController.navigate(UserScreenArgs(it.name, it.originalURL))
                }
            }
            composable<UserScreenArgs> {
                val args = it.toRoute<UserScreenArgs>()
                UserScreen(UserStateHolder(args.name, args.imageUrl)){
                    navController.popBackStack()
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SLNavPreview(){
    SLNav()
}