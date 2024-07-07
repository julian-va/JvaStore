package jva.cloud.jvastore.domain.model

import jva.cloud.jvastore.data.local.entity.UserEntity

data class User(
    val id: Long?,
    val userName: String,
    val email: String,
    val address: String,
    val dni: String,
)

fun userEntityToUser(entity: UserEntity): User {
    return User(
        id = entity.id,
        userName = entity.userName,
        dni = entity.dni,
        email = entity.email,
        address = entity.address
    )
}

fun userToUserEntity(model: User): UserEntity {
    return UserEntity(
        id = model.id,
        userName = model.userName,
        dni = model.dni,
        email = model.email,
        address = model.address
    )
}