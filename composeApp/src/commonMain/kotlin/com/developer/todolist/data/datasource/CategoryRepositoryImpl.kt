package com.developer.todolist.data.datasource

import com.developer.todolist.data.dao.CategoryDao
import com.developer.todolist.data.entity.CategoryEntity
import com.developer.todolist.domain.CategoryRepository
import com.developer.todolist.domain.model.CategoryWithCount
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(private val dao: CategoryDao): CategoryRepository {
    override suspend fun addCategory(categoryEntity: CategoryEntity) {
        dao.insert(categoryEntity)
    }

    override fun loadCategories(): Flow<List<CategoryWithCount>> {
        return dao.getAllAsFlow()
    }
}