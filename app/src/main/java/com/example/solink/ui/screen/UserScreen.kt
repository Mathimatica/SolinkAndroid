package com.example.solink.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.solink.R
import com.example.solink.ui.stateholder.UserStateHolder
import com.example.solink.ui.theme.SolinkTheme
import com.example.solink.ui.view.SLButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(stateHolder: UserStateHolder, onBackPressed: () -> Unit) {
    SolinkTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(SolinkTheme.spacing.medium),
            topBar = {
                TopAppBar(
                    title = { Text(text = "") }, // Empty title
                    navigationIcon = {
                        SLButton(onBackPressed) // Use SLButton as navigation icon
                    }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally, // Center-align the content
                    verticalArrangement = Arrangement.Center // Ensure it's vertically centered
                ) {
                    Text(
                        text = stateHolder.name,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineLarge // Adjust style as needed
                    )
                    Spacer(modifier = Modifier.height(SolinkTheme.spacing.medium)) // Space between name and image
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(stateHolder.imageUrl)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.placeholder_profile_image),
                        contentDescription = "Profile picture of [user's name]",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(SolinkTheme.profileImage.large) // Adjust size as needed
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserScreenPreview() {
    UserScreen(UserStateHolder("Josh", "")){

    }
}