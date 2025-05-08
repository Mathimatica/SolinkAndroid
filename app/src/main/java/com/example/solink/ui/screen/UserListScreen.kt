package com.example.solink.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.solink.R
import com.example.solink.ui.stateholder.StateHolder
import com.example.solink.ui.stateholder.UserListItemStateHolder
import com.example.solink.ui.stateholder.UserListStateHolder
import com.example.solink.ui.theme.SolinkTheme
import com.example.solink.viewmodel.UserListViewModel

@Composable
fun UserListScreen(photoItemClicked:(UserListItemStateHolder)->Unit) {
    val userViewModel: UserListViewModel = hiltViewModel()
    val stateHolder by userViewModel.stateHolder
    UserListScreen(stateHolder, photoItemClicked)
}

@Composable
private fun UserListScreen(stateHolder:StateHolder<UserListStateHolder>, photoItemClicked:(UserListItemStateHolder)->Unit) {

    SolinkTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(SolinkTheme.spacing.medium),
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
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(stateHolder.data.users) { user ->
                                UserListItemView(stateHolder = user) {
                                    photoItemClicked(user)
                                }
                            }
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

@Composable
private fun UserListItemView(stateHolder: UserListItemStateHolder, onClick:()->Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp).clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF0288D1), // A vibrant, trustworthy blue (Material's light blue 700)
            contentColor = Color.White,         // White for high contrast on the blue background
            disabledContainerColor = Color(0xFF01579B), // Darker blue (Material's light blue 900) for disabled state
            disabledContentColor = Color(0xFFB0BEC5)   // Light gray-blue (Material's blue-gray 200) for disabled content
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(SolinkTheme.spacing.medium), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stateHolder.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.weight(1f)
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(stateHolder.smallURL)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder_profile_image),
                contentDescription = "Image from URL",
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape).size(64.dp) // Adjust size as needed
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserScreenLoadingPreview() {
    UserListScreen(StateHolder.Loading){

    }
}

@Preview(showBackground = true)
@Composable
fun UserScreenSuccessPreview() {
    UserListScreen(StateHolder.Success(UserListStateHolder(listOf(
        UserListItemStateHolder("Josh"),
        UserListItemStateHolder("Matt"),
        UserListItemStateHolder("Nathan"),
        UserListItemStateHolder("Tiffany"),
        UserListItemStateHolder("Jason"),
        UserListItemStateHolder("Bob"),
        UserListItemStateHolder("Lisa"),
        UserListItemStateHolder("Henry"),
        UserListItemStateHolder("John"),
        UserListItemStateHolder("Kate"),
        UserListItemStateHolder("Josh"),
        UserListItemStateHolder("Matt"),
        UserListItemStateHolder("Nathan"),
        UserListItemStateHolder("Tiffany"),
        UserListItemStateHolder("Jason"),
        UserListItemStateHolder("Bob"),
        UserListItemStateHolder("Lisa"),
        UserListItemStateHolder("Henry"),
        UserListItemStateHolder("John"),
        UserListItemStateHolder("Kate"),
        UserListItemStateHolder("Josh"),
        UserListItemStateHolder("Matt"),
        UserListItemStateHolder("Nathan"),
        UserListItemStateHolder("Tiffany"),
        UserListItemStateHolder("Jason"),
        UserListItemStateHolder("Bob"),
        UserListItemStateHolder("Lisa"),
        UserListItemStateHolder("Henry"),
        UserListItemStateHolder("John"),
        UserListItemStateHolder("Kate")
    )))){

    }
}

@Preview(showBackground = true)
@Composable
fun UserScreenFailurePreview() {
    UserListScreen(StateHolder.Error("Unknown Error")){

    }
}