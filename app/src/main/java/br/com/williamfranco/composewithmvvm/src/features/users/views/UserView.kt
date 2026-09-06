package br.com.williamfranco.composewithmvvm.src.features.users.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.williamfranco.composewithmvvm.src.common.patterns.StatePattern
import br.com.williamfranco.composewithmvvm.src.features.users.exceptions.UserException
import br.com.williamfranco.composewithmvvm.src.features.users.models.UserModel
import br.com.williamfranco.composewithmvvm.src.features.users.view_models.UsersState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserView(
    userState: UsersState,
    onRefresh: () -> Unit,
    onOpenSettings: () -> Unit,
    onUserClick: (UserModel) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Users") },
                actions = {
                    IconButton(onClick = onRefresh) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                    IconButton(onClick = onOpenSettings) {
                        Icon(Icons.Outlined.Settings, contentDescription = "Settings")
                    }
                },
            )
        },
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = userState is StatePattern.Loading,
            onRefresh = onRefresh,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when (userState) {
                is StatePattern.Initial -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("List is empty.")
                    }
                }

                is StatePattern.Loading -> {
                    LazyColumn {
                        items(10) {
                            SkeletonItem()
                        }
                    }
                }

                is StatePattern.Success -> {
                    LazyColumn {
                        items(userState.data, key = { it.id ?: it.hashCode() }) { user ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                onClick = { onUserClick(user) },
                            ) {
                                ListItem(
                                    headlineContent = { Text(user.name.orEmpty()) },
                                    supportingContent = { Text(user.email.orEmpty()) },
                                    leadingContent = {
                                        Text(
                                            text = user.name?.firstOrNull()?.uppercase().orEmpty(),
                                            style = MaterialTheme.typography.titleMedium,
                                        )
                                    },
                                )
                            }
                        }
                    }
                }

                is StatePattern.Error -> {
                    ErrorContent(error = userState.error)
                }
            }
        }
    }
}

@Composable
private fun SkeletonItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(16.dp),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(12.dp)
                    .padding(top = 8.dp),
            )
        }
    }
}

@Composable
private fun ErrorContent(error: UserException) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text("Error: ${error.message}")
    }
}
