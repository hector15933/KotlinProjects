package com.example.taller03

import com.example.taller03.Model.UsersModel
import com.google.gson.annotations.SerializedName

data class UserResponse(

    @SerializedName("id")
    val id: String,
    @SerializedName("user_id")
    val user_id: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("user_image")
    val user_image: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("likes")
    val likes: String)
