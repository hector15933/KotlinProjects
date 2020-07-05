package com.example.taller03.Model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class UsersModel(
    val id: Int,
    val user_id: Int,
    val username: String,
    val user_image: String,
    val body: String,
    val image: String,
    val likes: Int,
    val comment: List<ComentModel>
)