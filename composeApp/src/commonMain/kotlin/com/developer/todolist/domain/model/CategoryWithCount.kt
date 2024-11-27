package com.developer.todolist.domain.model

data class CategoryWithCount(
    val id: Long,
    val title: String,
    val content: String,
    val count: Int
)