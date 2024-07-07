package jva.cloud.jvastore.data.local.repository

import jva.cloud.jvastore.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAll(): Flow<List<UserEntity>>
    suspend fun getById(idSearch: Int): UserEntity
    suspend fun saveAll(entities: List<UserEntity>)
    suspend fun delete(entity: UserEntity)
}