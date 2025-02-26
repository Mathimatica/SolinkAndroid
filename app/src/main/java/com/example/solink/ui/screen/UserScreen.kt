package com.example.solink.ui.screen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.solink.R
import com.example.solink.ui.StateHolder
import com.example.solink.ui.UserStateHolder
import com.example.solink.ui.theme.SolinkTheme

@Composable
fun UserScreen(stateHolder: StateHolder<UserStateHolder>, onBackPressed: () -> Unit) {
    SolinkTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(16.dp),
            topBar = {
                Button(onClick = onBackPressed) {
                    Text(text = "Back")
                }
            }
        ) { innerPadding ->
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
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally, // Center-align the content
                            verticalArrangement = Arrangement.Center // Ensure it's vertically centered
                        ) {
                            Text(
                                text = stateHolder.data.name,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.headlineLarge // Adjust style as needed
                            )
                            Spacer(modifier = Modifier.height(16.dp)) // Space between name and image
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .memoryCachePolicy(CachePolicy.DISABLED)
                                    .diskCachePolicy(CachePolicy.DISABLED)
                                    .networkCachePolicy(CachePolicy.ENABLED)
                                    .data(stateHolder.data.imageUrl)
                                    .crossfade(true)
                                    .build(),
                                placeholder = painterResource(R.drawable.placeholder_profile_image),
                                contentDescription = "Image from URL",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.clip(CircleShape).size(200.dp) // Adjust size as needed
                            )
                        }
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
    UserScreen(StateHolder.Loading){}
}

@Preview(showBackground = true)
@Composable
fun UserScreenSuccessPreview() {
    UserScreen(StateHolder.Success(UserStateHolder(name = "Josh", imageUrl = "TestUrl"))){}
}

@Preview(showBackground = true)
@Composable
fun UserScreenFailurePreview() {
    UserScreen(StateHolder.Error("Unknown Error")){}
}