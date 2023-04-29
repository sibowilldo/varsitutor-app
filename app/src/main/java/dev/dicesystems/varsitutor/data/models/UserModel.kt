package dev.dicesystems.varsitutor.data.models

import dev.dicesystems.varsitutor.data.remote.responses.UserX

data class UserModel(
    val token: String? = null,
    val user: UserX
)
