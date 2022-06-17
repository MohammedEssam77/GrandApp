package com.example.myapplication44.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication44.pojo.Albums
import com.example.myapplication44.pojo.Photo
import com.example.myapplication44.pojo.User
import com.example.myapplication44.repositry.UserRepository
import com.example.myapplication44.util.NetworkHelper
import com.example.myapplication44.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class UserViewModel
@Inject
constructor(
    private val repository: UserRepository, private val networkHelper: NetworkHelper) : ViewModel() {

    private val state = MutableLiveData<State<User>>()
    val mState: LiveData<State<User>>
        get() = state

    private val mstate = MutableLiveData<State<Albums>>()
    val nState: LiveData<State<Albums>>
        get() = nState

    private val photosState = MutableLiveData<State<Photo>>()
    val pState: LiveData<State<Photo>>
        get() = photosState

    fun getUser() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
            repository.getUser().collect {
                state.value = it
            }
            }
        }
    }
    fun getAlbum(userId: Int) {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                repository.getAlbum(userId).collect {
                    mstate.value = it
                }
            }
        }
    }
    fun getPhoto(albumId: Int) {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                repository.getPhotos(albumId).collect {
                    photosState.value = it
                }
            }
        }
    }
}