package br.com.williamfranco.composewithmvvm.src.di

import br.com.williamfranco.composewithmvvm.src.common.services.ConnectionService
import br.com.williamfranco.composewithmvvm.src.common.services.ConnectionServiceImpl
import br.com.williamfranco.composewithmvvm.src.common.services.HttpService
import br.com.williamfranco.composewithmvvm.src.features.settings.repositories.SettingRepository
import br.com.williamfranco.composewithmvvm.src.features.settings.repositories.SettingRepositoryImpl
import br.com.williamfranco.composewithmvvm.src.features.settings.repositories.settingsDataStore
import br.com.williamfranco.composewithmvvm.src.features.settings.view_models.SettingViewModel
import br.com.williamfranco.composewithmvvm.src.features.settings.view_models.SettingViewModelImpl
import br.com.williamfranco.composewithmvvm.src.features.users.repositories.UserRepository
import br.com.williamfranco.composewithmvvm.src.features.users.repositories.UserRepositoryImpl
import br.com.williamfranco.composewithmvvm.src.features.users.view_models.UserViewModel
import br.com.williamfranco.composewithmvvm.src.features.users.view_models.UserViewModelImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { HttpService() }
    single<ConnectionService> { ConnectionServiceImpl(get()) }
    single { androidContext().settingsDataStore }
    single<SettingRepository> { SettingRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    viewModelOf(::SettingViewModelImpl) { bind<SettingViewModel>() }
    viewModelOf(::UserViewModelImpl) { bind<UserViewModel>() }
}
