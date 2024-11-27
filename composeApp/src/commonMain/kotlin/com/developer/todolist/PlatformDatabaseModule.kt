package com.developer.todolist

import org.koin.core.module.Module

internal expect fun platformDatabaseModule(fileName: String): Module
