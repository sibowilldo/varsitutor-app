package dev.dicesystems.varsitutor.repository

import dev.dicesystems.varsitutor.data.local.entities.UserEntity
import dev.dicesystems.varsitutor.data.models.CreateApplicationModel
import dev.dicesystems.varsitutor.data.models.LoginModel
import dev.dicesystems.varsitutor.data.models.RegisterModel
import dev.dicesystems.varsitutor.data.remote.responses.Application
import dev.dicesystems.varsitutor.data.remote.responses.Applications
import dev.dicesystems.varsitutor.data.remote.responses.Favorites
import dev.dicesystems.varsitutor.data.remote.responses.Notifications
import dev.dicesystems.varsitutor.data.remote.responses.ResponseMessage
import dev.dicesystems.varsitutor.data.remote.responses.User
import dev.dicesystems.varsitutor.data.remote.responses.Vacancy
import dev.dicesystems.varsitutor.data.remote.responses.VacancyX
import dev.dicesystems.varsitutor.util.Resource
import retrofit2.Response

interface MainRepository {
    suspend fun login(loginModel: LoginModel): Response<User>
    suspend fun register(registerModel: RegisterModel): Response<User>
    suspend fun getLoggedInUser(): Response<User>
    suspend fun toggleFavorite(id: Int): Resource<ResponseMessage>
    suspend fun getVacancyList(page: Int = 1): Resource<Vacancy>
    suspend fun getNotificationList(page: Int = 1): Resource<Notifications>
    suspend fun getApplicationsList(): Resource<Applications>
    suspend fun getFavoritesList(): Resource<Favorites>
    suspend fun getVacancy(id: String): Resource<VacancyX>
    suspend fun registerUser(user: User): Resource<User>
    suspend fun createApplication(createApplicationModel: CreateApplicationModel): Resource<ResponseMessage>

    //UserDao
    suspend fun checkUserExists(token: String): Boolean
    suspend fun saveUser(userEntity: UserEntity): Unit
    suspend fun getLoggedInUserByToken(token: String): UserEntity
}