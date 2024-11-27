package com.developer.todolist.presentation.new_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.todolist.data.entity.TaskEntity
import com.developer.todolist.domain.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewTaskViewModel(
    private val id: Int,
    private val taskRepository: TaskRepository
) : ViewModel() {
    val titleText: MutableStateFlow<String> = MutableStateFlow<String>("")
    fun onConfirm() = viewModelScope.launch {
        taskRepository.addTask(TaskEntity(title = titleText.value, content = "", date = "", categoryId = id))
    }
}