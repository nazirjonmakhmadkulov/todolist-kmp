package com.developer.todolist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.developer.todolist.data.entity.CategoryEntity
import com.developer.todolist.data.entity.TaskEntity
import com.developer.todolist.domain.model.CategoryWithCount
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(item: CategoryEntity)

    @Query("SELECT count(*) FROM CategoryEntity")
    suspend fun count(): Int

    @Query(
        """
        SELECT c.id AS id, c.title AS title, c.content AS content, 
               COUNT(sc.id) AS count
        FROM CategoryEntity c
        LEFT JOIN TaskEntity sc ON c.id = sc.categoryId
        GROUP BY c.id, c.title
    """
    )
    fun getAllAsFlow(): Flow<List<CategoryWithCount>>
}