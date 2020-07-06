package com.example.taller03

import com.example.taller03.Model.UsersModel
import com.google.gson.annotations.SerializedName

data class UserResponse(
    val id: String,
    val username: String,
    val name: String,
    val lastname: String,
    val image: String,
    val occupation: String,
    val age: String,
    val email: String,
    val location:String,
    val phone:String,
    val social: Social)
