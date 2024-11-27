package com.developer.todolist.presentation.new_category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.todolist.data.entity.CategoryEntity
import com.developer.todolist.data.entity.TaskEntity
import com.developer.todolist.domain.CategoryRepository
import com.developer.todolist.domain.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewCategoryViewModel(
    private val taskRepository: CategoryRepository
) : ViewModel() {
    val titleText: MutableStateFlow<String> = MutableStateFlow<String>("")
    fun onConfirm() = viewModelScope.launch {
        taskRepository.addCategory(CategoryEntity(title = titleText.value, content = ""))
        titleText.value = ""
    }
}