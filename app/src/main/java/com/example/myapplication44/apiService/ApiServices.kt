package com.example.myapplication44.apiService

import com.example.myapplication44.pojo.Albums
import com.example.myapplication44.pojo.Photo
import com.example.myapplication44.pojo.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("/users")
    suspend fun getUsers(): Response<User>

    @GET("/albums")
    suspend fun getAlbums(@Query("userId")userId: Int): Response<Albums>

    @GET("/photos")
    suspend fun getPhotos(@Query("albumId")albumId: Int): Response<Photo>


}