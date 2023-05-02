package dev.dicesystems.varsitutor.data.remote.responses

data class Favorites(
    val favorites: List<Favorite>
) {
    data class Favorite(
        val created_at: CreatedAt,
        val id: Int,
        val vacancy: VacancyX
    )
}