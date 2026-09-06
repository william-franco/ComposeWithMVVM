package br.com.williamfranco.composewithmvvm.src.features.users.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.williamfranco.composewithmvvm.src.common.patterns.StatePattern
import br.com.williamfranco.composewithmvvm.src.features.users.exceptions.UserException
import br.com.williamfranco.composewithmvvm.src.features.users.models.UserModel
import br.com.williamfranco.composewithmvvm.src.features.users.repositories.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

typealias UsersState = StatePattern<List<UserModel>, UserException>

interface UserViewModel {
    val state: StateFlow<UsersState>
    fun getAllUsers()
    fun findUserById(userId: Int): UserModel?
}

class UserViewModelImpl(
    private val userRepository: UserRepository,
) : ViewModel(), UserViewModel {

    private val _state = MutableStateFlow<UsersState>(StatePattern.Initial)
    override val state: StateFlow<UsersState> = _state.asStateFlow()

    private var cachedUsers: List<UserModel> = emptyList()

    init {
        getAllUsers()
    }

    override fun getAllUsers() {
        viewModelScope.launch {
            _state.value = StatePattern.Loading

            val nextState = userRepository.findAllUsers().fold(
                onSuccess = { users ->
                    cachedUsers = users
                    StatePattern.Success(users)
                },
                onError = { error -> StatePattern.Error(error) },
            )

            _state.value = nextState
        }
    }

    override fun findUserById(userId: Int): UserModel? =
        cachedUsers.firstOrNull { it.id == userId }
}
