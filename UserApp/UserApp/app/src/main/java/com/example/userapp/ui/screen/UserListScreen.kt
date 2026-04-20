package com.example.userapp.ui.screen

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.userapp.data.model.User
import com.example.userapp.ui.components.UserItem
import com.example.userapp.ui.viewmodel.UserUiState
import com.example.userapp.ui.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(viewModel: UserViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()


    var selectedUser by remember { mutableStateOf<User?>(null) }


    AnimatedContent(
        targetState = selectedUser,
        transitionSpec = {
            if (targetState != null) {
                slideInHorizontally { it } + fadeIn() togetherWith slideOutHorizontally { -it } + fadeOut()
            } else {
                slideInHorizontally { -it } + fadeIn() togetherWith slideOutHorizontally { it } + fadeOut()
            }
        }, label = "ScreenTransition"
    ) { targetUser ->
        if (targetUser == null) {

            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text("Kullanıcı Listesi", fontWeight = FontWeight.Bold) }
                    )
                }
            ) { padding ->
                Box(modifier = Modifier.padding(padding).fillMaxSize()) {
                    when (val state = uiState) {
                        is UserUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        is UserUiState.Success -> {
                            LazyColumn {
                                items(state.users) { user ->
                                    UserItem(user = user, onClick = { selectedUser = user })
                                }
                            }
                        }
                        is UserUiState.Error -> {
                            Text(
                                text = state.message,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        } else {

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Kullanıcı Detayı", fontWeight = FontWeight.Bold) },
                        navigationIcon = {
                            IconButton(onClick = { selectedUser = null }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Geri")
                            }
                        }
                    )
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(20.dp)
                        .fillMaxSize()
                ) {

                    DetailCard(label = "Kullanıcı ID", value = "#${targetUser.id}", icon = Icons.Default.Info)
                    DetailCard(label = "Tam İsim", value = targetUser.name, icon = Icons.Default.Person)
                    DetailCard(label = "Kullanıcı Adı", value = targetUser.username, icon = Icons.Default.AccountCircle)
                    DetailCard(label = "E-posta", value = targetUser.email, icon = Icons.Default.Email)
                    DetailCard(label = "Telefon", value = targetUser.phone, icon = Icons.Default.Phone)
                    DetailCard(label = "Web Sitesi", value = targetUser.website, icon = Icons.Default.LocationOn)
                }
            }
        }
    }
}

@Composable
fun DetailCard(label: String, value: String, icon: ImageVector) {
    Row(
        modifier = Modifier.padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}