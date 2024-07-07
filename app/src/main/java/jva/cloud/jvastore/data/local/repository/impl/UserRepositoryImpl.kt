package jva.cloud.jvastore.data.local.repository.impl

import jva.cloud.jvastore.data.local.dao.UserDao
import jva.cloud.jvastore.data.local.entity.UserEntity
import jva.cloud.jvastore.data.local.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {
    override fun getAll(): Flow<List<UserEntity>> {
        return userDao.getAll()
    }

    override suspend fun getById(idSearch: Int): UserEntity {
        return userDao.getById(idSearch = idSearch)
    }

    override suspend fun saveAll(entities: List<UserEntity>) {
        userDao.saveAll(entities = entities)
    }

    override suspend fun delete(entity: UserEntity) {
        userDao.delete(entity = entity)
    }
}