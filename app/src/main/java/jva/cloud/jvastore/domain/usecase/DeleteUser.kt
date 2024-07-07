package jva.cloud.jvastore.domain.usecase

import jva.cloud.jvastore.domain.model.User

interface DeleteUser {
    suspend fun delete(model: User)
}