package dev.dicesystems.varsitutor.data.remote

import dev.dicesystems.varsitutor.data.remote.responses.Application
import dev.dicesystems.varsitutor.data.remote.responses.ApplicationX
import dev.dicesystems.varsitutor.data.remote.responses.Vacancy
import dev.dicesystems.varsitutor.data.remote.responses.VacancyX
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {
    @GET("vacancies")
    suspend fun getVacancyList(@Query("page") page: Int): Vacancy

    @GET("vacancies/{id}")
    suspend fun getVacancy(
        @Path("id") id: String
    ): VacancyX

    @GET("applications")
    suspend fun getApplicationList(@Query("page") page: Int): Application

    @GET("application/{user_id}")
    suspend fun getApplication(
        @Path("user_id") id: String
    ): ApplicationX


}