package com.example.taller03.Model

import com.example.taller03.Social

class UsuarioProfile(
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
        val social: Social
)