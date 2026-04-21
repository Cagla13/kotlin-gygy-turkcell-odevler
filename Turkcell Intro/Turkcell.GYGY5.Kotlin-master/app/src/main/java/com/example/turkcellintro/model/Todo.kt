package com.example.turkcellintro.model

@Serializable
data class Todo(
    val id: Int,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean
)
{

}

annotation class Serializable
