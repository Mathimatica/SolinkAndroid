package com.example.solink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.solink.ui.view.SLNav
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
class UserListScreenArgs(val pageNum:Int, val pagePer:Int)

@Serializable
class UserScreenArgs(val name:String, val imageUrl:String)