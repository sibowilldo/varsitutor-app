package dev.dicesystems.varsitutor.data.remote

import dev.dicesystems.varsitutor.data.remote.requests.LoginRequest
import dev.dicesystems.varsitutor.data.remote.responses.ResponseMessage
import dev.dicesystems.varsitutor.data.remote.responses.User
import dev.dicesystems.varsitutor.data.remote.responses.Vacancy
import dev.dicesystems.varsitutor.data.remote.responses.VacancyX
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {

    @Headers("Accept: application/json")
    @POST("auth/token")
    suspend fun login(@Body request: LoginRequest): User

    @Headers("Accept: application/json")
    @GET("user")
    suspend fun loggedInUser(): User

    @Headers("Accept: application/json")
    @POST("vacancies/{id}/favorite")
    suspend fun toggleFavorite(
        @Path("id") id: Int
    ): ResponseMessage

    @GET("vacancies")
    suspend fun getVacancyList(@Query("page") page: Int): Vacancy

    @GET("vacancies/{id}")
    suspend fun getVacancy(
        @Path("id") id: String
    ): VacancyX

    @POST("auth/register")
    suspend fun registerUser(
        @Body user: User
    ): User
}