package jva.cloud.jvastore.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jva.cloud.jvastore.data.local.entity.ProductsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {
    @Query(value = "SELECT * FROM products ORDER BY id DESC")
    fun getAll(): Flow<List<ProductsEntity>>

    @Query(value = "SELECT * FROM products WHERE id=:idSearch")
    suspend fun getById(idSearch: Int): ProductsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(entities: List<ProductsEntity>)
}