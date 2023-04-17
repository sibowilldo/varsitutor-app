package dev.dicesystems.varsitutor.data.models

import dev.dicesystems.varsitutor.data.remote.responses.CreatedAt
import dev.dicesystems.varsitutor.data.remote.responses.ExpiresAt

data class VacancyModel(
    val category: String,
    val created_at: CreatedAt,
    val department: String,
    val description: String,
    val expires_at: ExpiresAt,
    val id: Int,
    val location: String,
    val short_description: Int,
    val status: String,
    val title: String,
    val type: String,
    val user: String
)
