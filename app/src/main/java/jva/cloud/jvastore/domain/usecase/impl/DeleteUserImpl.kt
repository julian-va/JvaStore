package jva.cloud.jvastore.domain.usecase.impl

import jva.cloud.jvastore.data.local.repository.UserRepository
import jva.cloud.jvastore.domain.model.User
import jva.cloud.jvastore.domain.model.userToUserEntity
import jva.cloud.jvastore.domain.usecase.DeleteUser
import javax.inject.Inject

class DeleteUserImpl @Inject constructor(private val userRepository: UserRepository) : DeleteUser {
    override suspend fun delete(model: User) {
        userRepository.delete(entity = userToUserEntity(model = model))
    }
}