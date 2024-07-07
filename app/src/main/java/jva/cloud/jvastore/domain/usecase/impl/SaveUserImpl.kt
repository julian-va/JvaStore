package jva.cloud.jvastore.domain.usecase.impl

import jva.cloud.jvastore.data.local.repository.UserRepository
import jva.cloud.jvastore.domain.model.User
import jva.cloud.jvastore.domain.model.userToUserEntity
import jva.cloud.jvastore.domain.usecase.SaveUser
import javax.inject.Inject

class SaveUserImpl @Inject constructor(private val userRepository: UserRepository) : SaveUser {
    override suspend fun saveAll(models: List<User>) {
        val usersEntity = models.map { user -> userToUserEntity(model = user) }
        userRepository.saveAll(entities = usersEntity)
    }
}