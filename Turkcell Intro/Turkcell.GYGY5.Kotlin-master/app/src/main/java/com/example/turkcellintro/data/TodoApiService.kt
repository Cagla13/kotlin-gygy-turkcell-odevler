package com.example.turkcellintro.data

import com.example.turkcellintro.model.Todo
import retrofit2.http.GET

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>

}