package com.example.solink.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.solink.UserScreenArgs
import com.example.solink.repository.ApiResult
import com.example.solink.repository.PhotoRepository
import com.example.solink.repository.UserRepository
import com.example.solink.ui.StateHolder
import com.example.solink.ui.UserStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val photoRepository: PhotoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val stateHolder = mutableStateOf<StateHolder<UserStateHolder>>(StateHolder.Loading)

    init {
        val userArgs = savedStateHandle.toRoute<UserScreenArgs>()
        fetchData(userArgs)
    }

    private fun fetchData(args: UserScreenArgs) {
        stateHolder.value = StateHolder.Loading
        viewModelScope.launch {
            when(val userData = userRepository.fetchUserById(args.userId)){
                is ApiResult.Error -> {
                    stateHolder.value = StateHolder.Error(userData.message)
                }
                is ApiResult.Success -> {
                    when(val photoData = photoRepository.fetchPhotoById(args.pageNum, args.pagePer)){
                        is ApiResult.Error -> {
                            stateHolder.value = StateHolder.Error(photoData.message)
                        }
                        is ApiResult.Success -> {
                            stateHolder.value = StateHolder.Success(UserStateHolder(userData.data.name, photoData.data.photos.random().src.original))
                        }
                    }
                }
            }
        }
    }
}