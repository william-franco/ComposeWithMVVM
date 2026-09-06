package br.com.williamfranco.composewithmvvm.src.features.users.routes

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import br.com.williamfranco.composewithmvvm.src.features.users.view_models.UserViewModel
import br.com.williamfranco.composewithmvvm.src.features.users.view_models.UserViewModelImpl
import br.com.williamfranco.composewithmvvm.src.features.users.views.UserDetailView
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserDetailRoute(
    userId: Int,
    onBack: () -> Unit,
) {
    val activity = LocalContext.current as ComponentActivity
    val userViewModel: UserViewModel = koinViewModel<UserViewModelImpl>(viewModelStoreOwner = activity)
    val user = userViewModel.findUserById(userId)

    if (user != null) {
        UserDetailView(
            user = user,
            onBack = onBack,
        )
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("User not found.")
        }
    }
}
