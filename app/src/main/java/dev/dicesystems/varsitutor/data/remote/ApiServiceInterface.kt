package dev.dicesystems.varsitutor.data.remote

import dev.dicesystems.varsitutor.data.remote.responses.Vacancy
import dev.dicesystems.varsitutor.data.remote.responses.VacancyX
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {
    @GET("vacancies")
    suspend fun getVacancyList(@Query("page") page: Int): Vacancy

    @GET("vacancies/{id}")
    suspend fun getVacancy(
        @Path("id") id: String
    ): VacancyX
}