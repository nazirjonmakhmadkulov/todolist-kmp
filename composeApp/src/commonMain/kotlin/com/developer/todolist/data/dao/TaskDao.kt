package com.developer.todolist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.developer.todolist.data.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(item: TaskEntity)

    @Query("SELECT count(*) FROM TaskEntity")
    suspend fun count(): Int

    @Query("SELECT * FROM TaskEntity WHERE categoryId=:categoryId")
    fun getAllByCategoryId(categoryId: Int): Flow<List<TaskEntity>>
}