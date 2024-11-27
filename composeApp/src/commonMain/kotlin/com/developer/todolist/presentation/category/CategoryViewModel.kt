package com.developer.todolist.presentation.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.todolist.data.entity.CategoryEntity
import com.developer.todolist.data.entity.TaskEntity
import com.developer.todolist.domain.CategoryRepository
import com.developer.todolist.domain.TaskRepository
import com.developer.todolist.domain.model.CategoryWithCount
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: CategoryRepository) : ViewModel() {

    val categories: MutableSharedFlow<List<CategoryWithCount>> = MutableSharedFlow(
        1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    init {
        loadData()
    }

    fun loadData() = viewModelScope.launch {
        repository.loadCategories().collectLatest { categories.tryEmit(it) }
    }
}