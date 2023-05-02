package dev.dicesystems.varsitutor.repository

import android.util.Log
import dev.dicesystems.varsitutor.data.local.dao.UserDao
import dev.dicesystems.varsitutor.data.local.entities.UserEntity
import dev.dicesystems.varsitutor.data.models.CreateApplicationModel
import dev.dicesystems.varsitutor.data.models.LoginModel
import dev.dicesystems.varsitutor.data.models.RegisterModel
import dev.dicesystems.varsitutor.data.remote.ApiServiceInterface
import dev.dicesystems.varsitutor.data.remote.requests.LoginRequest
import dev.dicesystems.varsitutor.data.remote.requests.RegisterRequest
import dev.dicesystems.varsitutor.data.remote.responses.Application
import dev.dicesystems.varsitutor.data.remote.responses.Applications
import dev.dicesystems.varsitutor.data.remote.responses.Favorites
import dev.dicesystems.varsitutor.data.remote.responses.Notifications
import dev.dicesystems.varsitutor.data.remote.responses.ResponseMessage
import dev.dicesystems.varsitutor.data.remote.responses.User
import dev.dicesystems.varsitutor.data.remote.responses.Vacancy
import dev.dicesystems.varsitutor.data.remote.responses.VacancyX
import dev.dicesystems.varsitutor.util.Resource
import okhttp3.MultipartBody
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val api: ApiServiceInterface,
    private val userDao: UserDao
) : MainRepository {
    val TAG = "MAIN REPO TAG"
    override suspend fun login(loginModel: LoginModel): Response<User> {
        val response = try {
            api.login(LoginRequest(loginModel.email, loginModel.password, loginModel.device_name))
        } catch (e: HttpException) {
            return Response.error(e.code(), e.response()?.errorBody()!!)
        }

        return Response.success(response)
    }

    override suspend fun register(
        registerModel: RegisterModel
    ): Response<User> {
        val response = try {
            api.register(
                RegisterRequest(
                    registerModel.internal_identification,
                    registerModel.given_name,
                    registerModel.family_name,
                    registerModel.contact_number,
                    registerModel.province_city,
                    registerModel.password,
                    registerModel.password_confirmation,
                    registerModel.device_name
                )
            )
        } catch (e: HttpException) {
            Log.d(TAG, "register:")
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


    override suspend fun getNotificationList(page: Int): Resource<Notifications> {
        val response = try {
            api.getNotificationList(page)
        } catch (e: Exception) {
            return Resource.Error("Oops! Could not load Notifications ${e.message}")
        }

        return Resource.Success(data = response)
    }

    override suspend fun getApplicationsList(): Resource<Applications> {
        val response = try {
            api.getApplicationsList()
        } catch (e: Exception) {
            return Resource.Error("Oops! Could not load Applications ${e.message}")
        }

        return Resource.Success(data = response)
    }

    override suspend fun getFavoritesList(): Resource<Favorites> {
        val response = try {
            api.getFavoritesList()
        } catch (e: Exception) {
            return Resource.Error("Oops! Could not load Favorites ${e.message}")
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

    override suspend fun createApplication(createApplicationModel: CreateApplicationModel): Resource<ResponseMessage> {

        val appResponse = try {
            api.sendApplication(
                attachment = null,
                vacancy_id = MultipartBody.Part.createFormData(
                    "vacancy_id",
                    createApplicationModel.vacancy_id.toString()
                ),
                user_id = MultipartBody.Part.createFormData(
                    "user_id",
                    createApplicationModel.user_id.toString()
                ),
                contact_number = MultipartBody.Part.createFormData(
                    "contact_number",
                    createApplicationModel.contact_number
                ),
                email = MultipartBody.Part.createFormData(
                    "email",
                    createApplicationModel.email
                ),
                motivation = MultipartBody.Part.createFormData(
                    "motivation",
                    createApplicationModel.motivation
                ),
                job_title = MultipartBody.Part.createFormData(
                    "job_title",
                    createApplicationModel.job_title
                ),
                company_department = MultipartBody.Part.createFormData(
                    "company_department",
                    createApplicationModel.company_department
                ),
                duration = MultipartBody.Part.createFormData(
                    "duration",
                    createApplicationModel.duration
                )
            )
        } catch (e: HttpException) {
            return Resource.Error(
                "Oops! ${e.message}",
                data = ResponseMessage(message = e.message())
            )
        } catch (e: Exception) {
            Log.d(TAG, "toggleFavorite: ${e.message}")
            return Resource.Error("Oops! ${e.message}")
        }
        return Resource.Success(data = appResponse)
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