package org.sharad.emify

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.sharad.emify.core.di.initializeKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidContext(this@MyApplication)
        }
    }
}