package dev.haroldjose.simpleaudioplayerkmm.android

import android.app.Application
import dev.haroldjose.simpleaudioplayerkmm.android.di.androidModules
import dev.haroldjose.simpleaudioplayerkmm.di.sharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MainApplication)
            // Load modules
            modules(sharedModules() + androidModules)
        }

    }
}