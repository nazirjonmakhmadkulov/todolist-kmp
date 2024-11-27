package com.developer.todolist.di

import com.developer.todolist.data.AppDatabase
import com.developer.todolist.data.dao.CategoryDao
import com.developer.todolist.data.dao.TaskDao
import com.developer.todolist.data.datasource.CategoryRepositoryImpl
import com.developer.todolist.data.datasource.TaskRepositoryImpl
import com.developer.todolist.data.entity.CategoryEntity
import com.developer.todolist.domain.CategoryRepository
import com.developer.todolist.domain.TaskRepository
import com.developer.todolist.platformDatabaseModule
import com.developer.todolist.presentation.category.CategoryViewModel
import com.developer.todolist.presentation.new_category.NewCategoryViewModel
import com.developer.todolist.presentation.new_task.NewTaskViewModel
import com.developer.todolist.presentation.tasks.TasksViewModel
import kotlinx.coroutines.runBlocking
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private fun insertTop(dao: CategoryDao) = runBlocking {
    if (dao.count() <= 0) {
        dao.insert(CategoryEntity(1, "Мой день", ""))
        dao.insert(CategoryEntity(2, "Важное", ""))
        dao.insert(CategoryEntity(3, "Запланировано", ""))
        dao.insert(CategoryEntity(4, "Задачи", ""))
    }
}

val dbModule
    get() = platformDatabaseModule(fileName = "database.db")
        .apply {
            single<CategoryDao> { get<AppDatabase>().categoryDao().apply { insertTop(this) } }
            single<TaskDao> { get<AppDatabase>().taskDao() }

            fun provideCategoryRepository(categoryDao: CategoryDao) = CategoryRepositoryImpl(categoryDao)
            single<CategoryRepository> { provideCategoryRepository(get()) }

            fun provideTaskRepository(taskDao: TaskDao) = TaskRepositoryImpl(taskDao)
            single<TaskRepository> { provideTaskRepository(get()) }
        }

val vmModule
    get() = module {
        viewModelOf(::CategoryViewModel)
        viewModelOf(::NewCategoryViewModel)
        viewModelOf(::NewTaskViewModel)
        viewModelOf(::TasksViewModel)
    }