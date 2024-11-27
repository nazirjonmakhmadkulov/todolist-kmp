package com.developer.todolist.data.mapping

import com.developer.todolist.data.entity.CategoryEntity
import com.developer.todolist.data.entity.TaskEntity
import com.developer.todolist.domain.model.Category
import com.developer.todolist.domain.model.Task

fun TaskEntity.toModel() = Task(
    id = id,
    title = title,
    content = content,
    date = date,
    categoryId = categoryId,
    isComplete = isComplete,
    isImportantly = isImportantly,
)

fun CategoryEntity.toModel() = Category(
    id = id,
    title = title,
    content = content
)

fun Task.toEntity() = TaskEntity(
    id = id,
    title = title,
    content = content,
    date = date,
    categoryId = categoryId,
    isComplete = isComplete,
    isImportantly = isImportantly,
)

fun Category.toEntity() = CategoryEntity(
    id = id,
    title = title,
    content = content
)
