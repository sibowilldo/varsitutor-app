package dev.dicesystems.varsitutor.data.remote

import dev.dicesystems.varsitutor.data.remote.requests.LoginRequest
import dev.dicesystems.varsitutor.data.remote.requests.RegisterRequest
import dev.dicesystems.varsitutor.data.remote.responses.Applications
import dev.dicesystems.varsitutor.data.remote.responses.Favorites
import dev.dicesystems.varsitutor.data.remote.responses.Notifications
import dev.dicesystems.varsitutor.data.remote.responses.ResponseMessage
import dev.dicesystems.varsitutor.data.remote.responses.User
import dev.dicesystems.varsitutor.data.remote.responses.Vacancy
import dev.dicesystems.varsitutor.data.remote.responses.VacancyX
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {

    @Headers("Accept: application/json")
    @POST("auth/token")
    suspend fun login(@Body request: LoginRequest): User

    @Headers("Accept: application/json")
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): User

    @Multipart
    @Headers("Accept: application/json")
    @POST("applications")
    suspend fun sendApplication(
        @Part attachment: MultipartBody.Part?,
        @Part vacancy_id: MultipartBody.Part,
        @Part user_id: MultipartBody.Part,
        @Part contact_number: MultipartBody.Part,
        @Part email: MultipartBody.Part,
        @Part job_title: MultipartBody.Part,
        @Part duration: MultipartBody.Part,
        @Part company_department: MultipartBody.Part,
        @Part motivation: MultipartBody.Part,
    ): ResponseMessage

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

    @GET("users/applications")
    @Headers("Accept: application/json")
    suspend fun getApplicationsList(): Applications

    @GET("users/favorites")
    @Headers("Accept: application/json")
    suspend fun getFavoritesList(): Favorites


    @Headers("Accept: application/json")
    @GET("notifications")
    suspend fun getNotificationList(@Query("page") page: Int): Notifications

    @GET("vacancies/{id}")
    suspend fun getVacancy(
        @Path("id") id: String
    ): VacancyX

    @POST("auth/register")
    suspend fun registerUser(
        @Body user: User
    ): User
}