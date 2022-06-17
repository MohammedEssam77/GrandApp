package com.example.myapplication44.repositry

import com.example.myapplication44.apiService.ApiServices
import com.example.myapplication44.pojo.Albums
import com.example.myapplication44.pojo.Photo
import com.example.myapplication44.pojo.User
import com.example.myapplication44.util.NetworkBoundRepository
import com.example.myapplication44.util.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class UserRepository
@Inject
constructor  ( private val apiServices: ApiServices){
    fun getUser(): Flow<State<User>> {
        return object : NetworkBoundRepository<User>(){
            override suspend fun fetchFromRemote(): Response<User> =
                apiServices.getUsers()
            }.asFlow().flowOn(Dispatchers.IO)
        }
    fun getAlbum(userId: Int): Flow<State<Albums>> {
        return object : NetworkBoundRepository<Albums>(){
            override suspend fun fetchFromRemote(): Response<Albums> =
                apiServices.getAlbums(userId)
        }.asFlow().flowOn(Dispatchers.IO)
    }
    fun getPhotos(albumId: Int): Flow<State<Photo>> {
        return object : NetworkBoundRepository<Photo>(){
            override suspend fun fetchFromRemote(): Response<Photo> =
                apiServices.getPhotos(albumId)
        }.asFlow().flowOn(Dispatchers.IO)
    }
    }
