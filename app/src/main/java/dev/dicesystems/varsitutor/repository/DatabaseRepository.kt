package dev.dicesystems.varsitutor.repository

import android.util.Log
import dagger.hilt.android.scopes.ActivityScoped
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

@ActivityScoped
class DatabaseRepository @Inject constructor(
    private val userDao: UserDao
) {
    val TAG = "DB REPO TAG"

    fun getUserByToken(token: String): UserEntity {
        return userDao.findByToken(token)
    }

    fun checkUserExists(token: String): Boolean{
        return userDao.userExists(token) > 1
    }

    fun saveUser(userEntity: UserEntity) {
        return userDao.insert(userEntity)
    }
}