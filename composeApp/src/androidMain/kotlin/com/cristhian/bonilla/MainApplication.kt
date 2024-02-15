package com.cristhian.bonilla

import android.app.Application
import com.google.firebase.perf.metrics.AddTrace
import di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    @AddTrace(name = "onCreateTrace", enabled = true /* optional */)
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(sharedModule)
        }
    }
}