package br.com.williamfranco.composewithmvvm.src.features.users.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.williamfranco.composewithmvvm.src.features.users.models.UserModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailView(
    user: UserModel,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            DetailCard(title = user.name.orEmpty()) {
                Text("@${user.username.orEmpty()}", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(user.email.orEmpty(), modifier = Modifier.padding(top = 16.dp))
            }

            DetailCard(title = "Address") {
                Text("${user.address?.street}, ${user.address?.suite}")
                Text("${user.address?.city}, ${user.address?.zipcode}")
            }

            DetailCard(title = "Contact") {
                Text("Phone: ${user.phone.orEmpty()}")
                Text("Website: ${user.website.orEmpty()}")
            }

            DetailCard(title = "Company") {
                Text(user.company?.name.orEmpty())
                Text(user.company?.catchPhrase.orEmpty())
                Text(user.company?.bs.orEmpty())
            }
        }
    }
}

@Composable
private fun DetailCard(
    title: String,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            Column(modifier = Modifier.padding(top = 8.dp)) {
                content()
            }
        }
    }
}
