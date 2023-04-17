package dev.dicesystems.varsitutor.data.remote

import dev.dicesystems.varsitutor.data.models.Posts
import dev.dicesystems.varsitutor.data.models.VacancyModel
import dev.dicesystems.varsitutor.data.remote.responses.Vacancy
import dev.dicesystems.varsitutor.data.remote.responses.VacancyX
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {
    @GET("vacancy")
    suspend fun getVacancyList(@Query("page") page: Int): Vacancy

    @GET("vacancy/{id}")
    suspend fun getVacancy(
        @Path("id") id: String
    ): VacancyX
}