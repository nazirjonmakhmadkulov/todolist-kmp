package com.developer.todolist.data.datasource

import com.developer.todolist.data.dao.TaskDao
import com.developer.todolist.data.entity.TaskEntity
import com.developer.todolist.domain.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(private val dao: TaskDao) : TaskRepository {
    override suspend fun addTask(taskEntity: TaskEntity) = dao.insert(taskEntity)

    override fun loadTasks(categoryId: Int): Flow<List<TaskEntity>> {
        return dao.getAllByCategoryId(categoryId)
    }
}