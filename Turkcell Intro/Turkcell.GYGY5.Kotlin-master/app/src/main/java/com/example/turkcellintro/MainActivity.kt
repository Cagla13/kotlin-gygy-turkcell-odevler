package com.example.turkcellintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.turkcellintro.model.Todo
import com.example.turkcellintro.viewmodel.ToDoListViewModel

sealed class Screen(val route: String) {
    data object Register : Screen("register")
    data object Homepage : Screen("homepage")
    data object AddTodo : Screen("add_todo")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { paddingValues ->
                MyNavigatableApp(Modifier.padding(paddingValues))
            }
        }
    }
}

@Composable
fun MyNavigatableApp(modifier: Modifier) {
    val navController = rememberNavController()

    val todoViewModel: ToDoListViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Homepage.route) {
        composable(Screen.Register.route) { RegisterScreen(modifier, navController) }


        composable(Screen.Homepage.route) {
            Homepage(modifier, navController, todoViewModel)
        }


        composable(Screen.AddTodo.route) {
            AddToDoPage(modifier, navController, todoViewModel)
        }
    }
}

@Composable
fun Homepage(modifier: Modifier, navController: NavController, todoViewModel: ToDoListViewModel) {
    val todos by todoViewModel.todos.collectAsState()
    val isLoading by todoViewModel.isLoading.collectAsState()
    val error by todoViewModel.error.collectAsState()

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text("Yapılacaklar Listesi", style = MaterialTheme.typography.headlineLarge)

        Box(modifier = Modifier.weight(1f)) {
            when {
                isLoading -> { CircularProgressIndicator(modifier = Modifier.align(Alignment.Center)) }
                error != null -> { Text("Hata: $error", color = MaterialTheme.colorScheme.error) }
                else -> {
                    ToDoList(todos, onDelete = { id -> todoViewModel.delete(id) })
                }
            }
        }


        Button(
            onClick = { navController.navigate(Screen.AddTodo.route) },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Text("Yeni Görev Ekle")
        }
    }
}


@Composable
fun AddToDoPage(modifier: Modifier, navController: NavController, viewModel: ToDoListViewModel) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Ne Eklemek İstersin?", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Görev Başlığı") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (text.isNotBlank) {
                viewModel.addTodo(text)
                navController.popBackStack()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Kaydet")
        }
    }
}

@Composable
fun ToDoList(toDoList: List<Todo>, onDelete: (Int) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(toDoList) { _, todo ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(todo.title)
                IconButton(onClick = { onDelete(todo.id) }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Sil")
                }
            }
        }
    }
}