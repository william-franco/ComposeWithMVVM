package br.com.williamfranco.composewithmvvm.src.fakes

import br.com.williamfranco.composewithmvvm.src.common.patterns.ResultPattern
import br.com.williamfranco.composewithmvvm.src.features.settings.models.SettingModel
import br.com.williamfranco.composewithmvvm.src.features.settings.repositories.SettingRepository
import br.com.williamfranco.composewithmvvm.src.features.users.exceptions.UserException
import br.com.williamfranco.composewithmvvm.src.features.users.models.UserModel
import br.com.williamfranco.composewithmvvm.src.features.users.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeUserRepository(
    private val result: ResultPattern<List<UserModel>, UserException>,
) : UserRepository {
    override suspend fun findAllUsers(): ResultPattern<List<UserModel>, UserException> = result
}

class FakeSettingRepository(
    initialTheme: SettingModel = SettingModel(),
) : SettingRepository {

    private val themeState = MutableStateFlow(initialTheme)
    override val theme: Flow<SettingModel> = themeState.asStateFlow()

    override suspend fun readTheme(): SettingModel = themeState.value

    override suspend fun updateTheme(isDarkTheme: Boolean) {
        themeState.value = SettingModel(isDarkTheme = isDarkTheme)
    }
}
