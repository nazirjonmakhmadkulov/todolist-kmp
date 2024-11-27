package com.developer.todolist

import android.app.Application
import com.developer.todolist.di.dbModule
import com.developer.todolist.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        stopKoin()
        startKoin {
            androidContext(this@App)
            modules(dbModule, vmModule)
        }
    }
}