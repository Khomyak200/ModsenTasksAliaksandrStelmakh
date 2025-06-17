package com.example.modsen_tasks_aliaksandrstelmakh.di.application

import android.app.Application
import com.example.modsen_tasks_aliaksandrstelmakh.di.modules.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            val appModule = MainModule();
            val appm = appModule.appModule
            modules(appm)
        }
    }
}