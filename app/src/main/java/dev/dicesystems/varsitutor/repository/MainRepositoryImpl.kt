package dev.dicesystems.varsitutor.repository

import android.util.Log
import dev.dicesystems.varsitutor.data.local.dao.UserDao
import dev.dicesystems.varsitutor.data.local.entities.UserEntity
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
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val api: ApiServiceInterface,
    private val userDao: UserDao
): MainRepository{
    val TAG = "MAIN REPO TAG"
    override suspend fun login(
        email: String,
        password: String,
        device_name: String
    ): Response<User> {
        val response = try {
            api.login(LoginRequest(email, password, device_name))
        } catch (e: HttpException) {
            return Response.error(e.code(), e.response()?.errorBody()!!)
        }

        return Response.success(response)
    }

    override suspend fun getLoggedInUser(): Response<User> {
        val response = try {
            api.loggedInUser()
        } catch (e: HttpException) {
            return Response.error(e.code(), e.response()?.errorBody()!!)
        }
        Log.d(TAG, "getLoggedInUser: ${response.token}")
        return Response.success(response)
    }

    override suspend fun toggleFavorite(id: Int): Resource<ResponseMessage> {
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

    override suspend fun getVacancyList(page: Int): Resource<Vacancy> {
        val response = try {
            api.getVacancyList(page)
        } catch (e: Exception) {
            return Resource.Error("Oops! Could not load Vacancies ${e.message}")
        }

        return Resource.Success(data = response)
    }

    override suspend fun getVacancy(id: String): Resource<VacancyX> {
        val response = try {
            api.getVacancy(id)
        } catch (e: Exception) {
            return Resource.Error("Oops! Could find requested Vacancy")
        }
        return Resource.Success(data = response)
    }

    override suspend fun registerUser(user: User): Resource<User> {
        val response = try {
            api.registerUser(user)
        } catch (e: Exception) {
            return Resource.Error("Failed to register user")
        }
        Log.d("TAG", "registerUser: ${response.user}")
        return Resource.Success(data = response)
    }

    override suspend fun checkUserExists(token: String): Boolean {
        return userDao.userExists(token) > 1
    }

    override suspend fun saveUser(userEntity: UserEntity) {
        userDao.insert(userEntity)
    }

    override suspend fun getLoggedInUserByToken(token: String): UserEntity {
        return userDao.findByToken(token)
    }
}