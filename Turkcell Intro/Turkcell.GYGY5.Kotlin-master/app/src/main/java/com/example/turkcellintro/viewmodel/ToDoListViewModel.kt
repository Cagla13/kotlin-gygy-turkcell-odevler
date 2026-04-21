package com.example.turkcellintro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turkcellintro.data.TodoRepository
import com.example.turkcellintro.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Collections.emptyList


class ToDoListViewModel : ViewModel() {
    private val repository = TodoRepository()

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        val launch = viewModelScope.launch {
            ->
            _isLoading.value = true
            _error.value = null
            try {
                val result = repository.getTodos()
                _todos.value = result
            } catch (e: Exception) {
                _error.value = e.message ?: "Bir hata oluştu."
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            try {
                repository.delete(id)

                fetchTodos()
            } catch (e: Exception) {
                _error.value = "Silme işlemi başarısız: ${e.message}"
            }
        }
    }

    fun addTodo(title: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {

                val newTodo = Todo(
                    id = 0,
                    title = title,
                    isCompleted = false
                )
                repository.addToDo(newTodo)

                fetchTodos()
            } catch (e: Exception) {
                _error.value = "Ekleme işlemi başarısız: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}