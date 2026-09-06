package br.com.williamfranco.composewithmvvm

import android.app.Application
import br.com.williamfranco.composewithmvvm.src.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ComposeWithMvvmApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@ComposeWithMvvmApplication)
            modules(appModule)
        }
    }
}
