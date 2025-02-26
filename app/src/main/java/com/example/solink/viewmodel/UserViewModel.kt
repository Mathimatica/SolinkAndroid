package com.example.solink.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.solink.UserScreenArgs
import com.example.solink.network.data.UserResponse
import com.example.solink.repository.ApiResult
import com.example.solink.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class StateHolder<out T> {
    data object Loading : StateHolder<Nothing>()
    data class Success<T>(val data: T) : StateHolder<T>()
    data class Error(val message: String?) : StateHolder<Nothing>()
}

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val stateHolder = mutableStateOf<StateHolder<UserResponse>>(StateHolder.Loading)

    init {
        val userArgs = savedStateHandle.toRoute<UserScreenArgs>()
        getUserById(userArgs.userId)
    }

    fun getUserById(userId: Int) {
        stateHolder.value = StateHolder.Loading
        viewModelScope.launch {
            when(val result = userRepository.fetchUserById(userId)){
                is ApiResult.Error -> {
                    stateHolder.value = StateHolder.Error(result.message)
                }
                is ApiResult.Success -> {
                    stateHolder.value = StateHolder.Success(result.data)
                }
            }
        }
    }
}