package jva.cloud.jvastore.domain.usecase

import jva.cloud.jvastore.domain.model.User
import kotlinx.coroutines.flow.Flow

interface RetrieveAllUser {
    fun getAll(): Flow<List<User>>
}