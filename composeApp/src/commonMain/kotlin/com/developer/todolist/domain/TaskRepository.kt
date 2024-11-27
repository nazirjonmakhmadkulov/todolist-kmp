package com.developer.todolist.domain

import com.developer.todolist.data.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun addTask(taskEntity: TaskEntity)
    fun loadTasks(categoryId: Int): Flow<List<TaskEntity>>
}