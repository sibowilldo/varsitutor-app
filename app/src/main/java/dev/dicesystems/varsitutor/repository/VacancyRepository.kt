package dev.dicesystems.varsitutor.repository

import android.util.Log
import dagger.hilt.android.scopes.ActivityScoped
import dev.dicesystems.varsitutor.data.remote.ApiServiceInterface
import dev.dicesystems.varsitutor.data.remote.responses.Vacancy
import dev.dicesystems.varsitutor.data.remote.responses.VacancyX
import dev.dicesystems.varsitutor.util.Resource
import javax.inject.Inject

@ActivityScoped
class VacancyRepository @Inject constructor(
    private val api: ApiServiceInterface
) {
    suspend fun getVacancyList(page: Int = 1): Resource<Vacancy>{

        val response = try {
            api.getVacancyList(page)
        }catch(e: Exception){
            return Resource.Error("Oops! Could not load Vacancies ${e.message}")
        }
        return Resource.Success(data = response)
    }

    suspend fun getVacancy(id: String): Resource<VacancyX>{
        val response = try {
            api.getVacancy(id)
        }catch(e: Exception){
            return Resource.Error("Oops! Could find requested Vacancy")
        }
        return Resource.Success(data = response)
    }
}