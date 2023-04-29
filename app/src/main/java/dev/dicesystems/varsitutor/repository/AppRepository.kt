package dev.dicesystems.varsitutor.repository

import android.util.Log
import dagger.hilt.android.scopes.ActivityScoped
import dev.dicesystems.varsitutor.data.remote.ApiServiceInterface
import dev.dicesystems.varsitutor.data.remote.requests.LoginRequest
import dev.dicesystems.varsitutor.data.remote.responses.ResponseMessage
import dev.dicesystems.varsitutor.data.remote.responses.User
import dev.dicesystems.varsitutor.data.remote.responses.Vacancy
import dev.dicesystems.varsitutor.data.remote.responses.VacancyX
import dev.dicesystems.varsitutor.util.Resource
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

@ActivityScoped
class AppRepository @Inject constructor(
    private val api: ApiServiceInterface
) {
    val TAG = "APP REPO TAG"
    suspend fun login(email: String, password: String, device_name: String): Response<User> {
        val response = try {
            api.login(LoginRequest(email, password, device_name))
        } catch (e: HttpException) {
            return Response.error(e.code(), e.response()?.errorBody()!!)
        }

        return Response.success(response)
    }

    suspend fun getLoggedInUser(): Response<User> {
        val response = try {
            api.loggedInUser()
        } catch (e: HttpException) {
            return Response.error(e.code(), e.response()?.errorBody()!!)
        }
        Log.d(TAG, "getLoggedInUser: ${response.token}")
        return Response.success(response)
    }

    suspend fun toggleFavorite(id: Int): Resource<ResponseMessage> {
        val response = try {
            api.toggleFavorite(id)
        } catch (e: HttpException) {
            return Resource.Error(
                "Oops! ${e.message}",
                data = ResponseMessage(message = e.message())
            )
        } catch (e: Exception) {
            Log.d(TAG, "toggleFavorite: ${e.message}")
            return Resource.Error("Oops! ${e.message}")
        }
        return Resource.Success(data = response)
    }

    suspend fun getVacancyList(page: Int = 1): Resource<Vacancy> {

        val response = try {
            api.getVacancyList(page)
        } catch (e: Exception) {
            return Resource.Error("Oops! Could not load Vacancies ${e.message}")
        }

        return Resource.Success(data = response)
    }

    suspend fun getVacancy(id: String): Resource<VacancyX> {
        val response = try {
            api.getVacancy(id)
        } catch (e: Exception) {
            return Resource.Error("Oops! Could find requested Vacancy")
        }
        return Resource.Success(data = response)
    }

    suspend fun registerUser(user: User): Resource<User> {
        val response = try {
            api.registerUser(user)
        } catch (e: Exception) {
            return Resource.Error("Failed to register user")
        }
        Log.d("TAG", "registerUser: ${response.user}")
        return Resource.Success(data = response)
    }
}