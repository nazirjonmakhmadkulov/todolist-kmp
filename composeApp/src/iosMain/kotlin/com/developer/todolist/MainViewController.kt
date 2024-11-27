package com.developer.todolist

import androidx.compose.ui.window.ComposeUIViewController
import com.developer.todolist.di.dbModule
import com.developer.todolist.di.vmModule
import org.koin.core.context.startKoin

@Suppress("unused", "FunctionName")
fun MainViewController() = ComposeUIViewController { ComposeApp() }

@Suppress("unused")
fun initApp() {
    initKoin()
}

private fun initKoin() {
    startKoin { modules(dbModule, vmModule) }
}