package jva.cloud.jvastore.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jva.cloud.jvastore.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query(value = "SELECT * FROM users ORDER BY id DESC")
    fun getAll(): Flow<List<UserEntity>>

    @Query(value = "SELECT * FROM users WHERE id=:idSearch")
    suspend fun getById(idSearch: Int): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(entities: List<UserEntity>)

    @Delete
    suspend fun delete(entity: UserEntity)
}