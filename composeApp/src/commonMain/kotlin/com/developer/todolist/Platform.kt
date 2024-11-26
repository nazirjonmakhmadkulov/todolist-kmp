package com.developer.todolist

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform