package vn.nvp.themoviedbapi

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import vn.nvp.themoviedbapi.di.appModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        // Init DI
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}
