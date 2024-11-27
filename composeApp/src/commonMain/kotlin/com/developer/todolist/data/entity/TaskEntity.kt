package com.developer.todolist.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
    val date: String,
    val categoryId: Int,
    val isComplete: Int = 0,
    val isImportantly: Int = 0,
)