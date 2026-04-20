package com.example.userapp.data.repository

import com.example.userapp.data.model.User
import com.example.userapp.data.remote.RetrofitInstance

class UserRepository {
    suspend fun getUsers(): List<User> = RetrofitInstance.api.getUsers()
}