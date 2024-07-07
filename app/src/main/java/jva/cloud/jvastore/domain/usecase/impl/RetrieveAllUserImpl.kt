package jva.cloud.jvastore.domain.usecase.impl

import jva.cloud.jvastore.data.local.repository.UserRepository
import jva.cloud.jvastore.domain.model.User
import jva.cloud.jvastore.domain.model.userEntityToUser
import jva.cloud.jvastore.domain.usecase.RetrieveAllUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RetrieveAllUserImpl @Inject constructor(private val userRepository: UserRepository) :
    RetrieveAllUser {
    override fun getAll(): Flow<List<User>> {
        return userRepository.getAll()
            .map { usersEntityFlow -> usersEntityFlow.map { userEntity -> userEntityToUser(entity = userEntity) } }
    }
}