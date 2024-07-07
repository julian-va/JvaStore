package jva.cloud.jvastore.domain.usecase

import jva.cloud.jvastore.domain.model.User

interface SaveUser {
    suspend fun saveAll(models: List<User>)
}