package com.developer.todolist.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.todolist.data.entity.TaskEntity
import com.developer.todolist.domain.TaskRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TasksViewModel(
    private val categoryId: Int,
    private val repository: TaskRepository
) : ViewModel() {

    val tasks: MutableSharedFlow<List<TaskEntity>> = MutableSharedFlow(
        1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    init {
        loadData()
    }

    fun loadData() = viewModelScope.launch {
        repository.loadTasks(categoryId).collectLatest { tasks.tryEmit(it) }
    }
}