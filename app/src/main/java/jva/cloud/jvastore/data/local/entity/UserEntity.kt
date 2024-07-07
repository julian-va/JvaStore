package jva.cloud.jvastore.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import jva.cloud.jvastore.util.ConstantApp.BOOLEAN_TRUE

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = BOOLEAN_TRUE)
    @ColumnInfo(name = "id")
    val id: Long?,
    @ColumnInfo(name = "user_name")
    val userName: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "dni")
    val dni: String,
)
