package dev.dicesystems.varsitutor.repository

import dagger.hilt.android.scopes.ActivityScoped
import dev.dicesystems.varsitutor.data.remote.ApiServiceInterface
import dev.dicesystems.varsitutor.data.remote.responses.Application
import dev.dicesystems.varsitutor.data.remote.responses.ApplicationX
import dev.dicesystems.varsitutor.util.Resource
import javax.inject.Inject

@ActivityScoped
class ApplicationRepository @Inject constructor(
    private val api: ApiServiceInterface
) {
    suspend fun getApplicationList(page: Int = 1): Resource<Application>{

        val response = try {
            api.getApplicationList(page)
        }catch(e: Exception){
            return Resource.Error("Oops! Could not load Applications ${e.message}")
        }

        return Resource.Success(data = response)
    }

    suspend fun getApplication(id: String): Resource<ApplicationX>{
        val response = try {
            api.getApplication(id)
        }catch(e: Exception){
            return Resource.Error("Oops! Could find requested Application")
        }
        return Resource.Success(data = response)
    }
}