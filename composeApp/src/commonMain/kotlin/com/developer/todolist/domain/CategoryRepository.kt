package com.developer.todolist.domain

import com.developer.todolist.data.entity.CategoryEntity
import com.developer.todolist.domain.model.CategoryWithCount
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun addCategory(categoryEntity: CategoryEntity)
    fun loadCategories(): Flow<List<CategoryWithCount>>
}