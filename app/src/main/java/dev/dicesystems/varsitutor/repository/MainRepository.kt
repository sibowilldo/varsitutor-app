package dev.dicesystems.varsitutor.repository

import dev.dicesystems.varsitutor.data.local.entities.UserEntity
import dev.dicesystems.varsitutor.data.remote.responses.ResponseMessage
import dev.dicesystems.varsitutor.data.remote.responses.User
import dev.dicesystems.varsitutor.data.remote.responses.Vacancy
import dev.dicesystems.varsitutor.data.remote.responses.VacancyX
import dev.dicesystems.varsitutor.util.Resource
import retrofit2.Response

interface MainRepository {
    suspend fun login(email: String, password: String, device_name: String): Response<User>
    suspend fun getLoggedInUser(): Response<User>
    suspend fun toggleFavorite(id: Int): Resource<ResponseMessage>
    suspend fun getVacancyList(page: Int = 1): Resource<Vacancy>
    suspend fun getVacancy(id: String): Resource<VacancyX>
    suspend fun registerUser(user: User): Resource<User>

    //UserDao
    suspend fun checkUserExists(token: String): Boolean
    suspend fun saveUser(userEntity: UserEntity): Unit
    suspend fun getLoggedInUserByToken(token: String): UserEntity
}