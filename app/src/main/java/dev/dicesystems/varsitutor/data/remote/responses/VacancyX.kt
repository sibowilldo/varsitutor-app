package dev.dicesystems.varsitutor.data.remote.responses

data class VacancyX(
    val category: String,
    val created_at: CreatedAt,
    val department: String,
    val description: String,
    val expires_at: ExpiresAt,
    val id: Int,
    val location: String,
    val short_description: String,
    val status: String,
    val title: String,
    val type: String,
    val user: String
)