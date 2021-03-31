package digital.gok.sandbox

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SandboxApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@SandboxApplication)
            modules(applicationModules)
        }
    }

}