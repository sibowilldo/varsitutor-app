package dev.dicesystems.varsitutor.data.models

import dev.dicesystems.varsitutor.data.remote.responses.CreatedAt
import dev.dicesystems.varsitutor.data.remote.responses.VacancyX

data class FavoriteModel(
    val id: Int,
    val created_at: CreatedAt,
    val vacancy: VacancyX
)