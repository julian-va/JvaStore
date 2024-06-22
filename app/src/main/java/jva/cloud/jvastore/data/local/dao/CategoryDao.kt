package jva.cloud.jvastore.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jva.cloud.jvastore.data.local.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Query(value = "SELECT * FROM categories ORDER BY id DESC")
    suspend fun getAll(): List<CategoryEntity>

    @Query(value = "SELECT * FROM categories WHERE id=:idSearch")
    suspend fun getById(idSearch: Int): CategoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(entities: List<CategoryEntity>)
}