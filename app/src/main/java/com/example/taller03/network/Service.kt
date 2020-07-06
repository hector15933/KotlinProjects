package com.example.taller03

import com.example.taller03.Model.UsersModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface Service {
    @GET("posts")
    fun fetchAllUsers() : Call<List<UsersModel>>


    @GET("profile")
    suspend fun getProfile(): Response<UserResponse>

    @GET("posts")
    suspend fun getPosts(): Response<List<UsersModel>>



}