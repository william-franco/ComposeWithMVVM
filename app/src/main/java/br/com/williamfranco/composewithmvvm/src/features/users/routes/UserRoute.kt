package br.com.williamfranco.composewithmvvm.src.features.users.routes

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.williamfranco.composewithmvvm.src.features.users.models.UserModel
import br.com.williamfranco.composewithmvvm.src.features.users.view_models.UserViewModel
import br.com.williamfranco.composewithmvvm.src.features.users.view_models.UserViewModelImpl
import br.com.williamfranco.composewithmvvm.src.features.users.views.UserView
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserRoute(
    onOpenSettings: () -> Unit,
    onUserClick: (UserModel) -> Unit,
) {
    val activity = LocalContext.current as ComponentActivity
    val userViewModel: UserViewModel = koinViewModel<UserViewModelImpl>(viewModelStoreOwner = activity)
    val userState by userViewModel.state.collectAsStateWithLifecycle()

    UserView(
        userState = userState,
        onRefresh = userViewModel::getAllUsers,
        onOpenSettings = onOpenSettings,
        onUserClick = onUserClick,
    )
}
