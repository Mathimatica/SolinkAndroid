package com.example.solink.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.solink.UserListScreenArgs
import com.example.solink.network.ApiResult
import com.example.solink.network.data.Photo
import com.example.solink.repository.PhotoRepository
import com.example.solink.ui.stateholder.StateHolder
import com.example.solink.ui.stateholder.UserListItemStateHolder
import com.example.solink.ui.stateholder.UserListStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val stateHolder = mutableStateOf<StateHolder<UserListStateHolder>>(StateHolder.Loading)

    var photoItemClicked:(Photo)->Unit = {  }

    init {
        val userArgs = savedStateHandle.toRoute<UserListScreenArgs>()
        fetchData(userArgs)
    }

    private fun fetchData(args: UserListScreenArgs) {
        stateHolder.value = StateHolder.Loading
        viewModelScope.launch {
            when(val photoData = photoRepository.fetchPhotoById(args.pageNum, args.pagePer)){
                is ApiResult.Error -> {
                    stateHolder.value = StateHolder.Error(photoData.message)
                }
                is ApiResult.Success -> {
                    stateHolder.value = StateHolder.Success(UserListStateHolder(photoData.data.photos.map { UserListItemStateHolder(it.photographer, it.src.small){
                        photoItemClicked(it)
                    } }))
                }
            }
        }
    }
}