package com.developer.todolist.domain.model

data class Task(
    val id: Long,
    val title: String,
    val content: String,
    val date: String,
    val categoryId: Int,
    val isComplete: Int = 0,
    val isImportantly: Int = 0,
)