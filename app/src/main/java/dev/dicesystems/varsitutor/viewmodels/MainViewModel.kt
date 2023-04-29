package dev.dicesystems.varsitutor.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dicesystems.varsitutor.R
import dev.dicesystems.varsitutor.data.local.entities.UserEntity
import dev.dicesystems.varsitutor.data.models.VacancyModel
import dev.dicesystems.varsitutor.data.remote.responses.ResponseMessage
import dev.dicesystems.varsitutor.data.remote.responses.User
import dev.dicesystems.varsitutor.data.sessions.UserSession
import dev.dicesystems.varsitutor.repository.MainRepositoryImpl
import dev.dicesystems.varsitutor.util.PreferenceManager
import dev.dicesystems.varsitutor.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepositoryImpl: MainRepositoryImpl
) : ViewModel() {

    val TAG = "VIEW MODEL TAG"

    private var currentPage = 1

    var vacancyList = mutableStateOf<List<VacancyModel>>(listOf())
    var responseMessage = mutableStateOf<ResponseMessage>(ResponseMessage(""))
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadVacancyPaginatedList()
    }

    fun doToggleFavorite(id: Int, context: Context) {
        viewModelScope.launch {
            isLoading.value = true
            when (val results = mainRepositoryImpl.toggleFavorite(id)) {
                is Resource.Success -> {
                    Log.d(TAG, "doToggleFavorite: ${results.data?.message}")
                    loadError.value = ""
                    isLoading.value = false
                    Toast.makeText(context, results.data?.message!!, Toast.LENGTH_SHORT).show()
                }

                is Resource.Error -> {
                    Toast.makeText(context, results.data?.message!!, Toast.LENGTH_SHORT).show()
                    isLoading.value = false
                    loadError.value = results.data.message
                }

                else -> {
                    Toast.makeText(context, results.data?.message!!, Toast.LENGTH_SHORT).show()
                    loadError.value = results.data.message
                    isLoading.value = false
                }
            }
        }
    }

    fun loadVacancyPaginatedList() {
        viewModelScope.launch {

            isLoading.value = true
            when (val results = mainRepositoryImpl.getVacancyList(currentPage)) {
                is Resource.Success -> {
                    endReached.value = currentPage >= 5// results.data!!.meta?.last_page!!
                    val vacancyEntries = results.data!!.data.mapIndexed { _, entry ->
                        VacancyModel(
                            category = entry.category,
                            created_at = entry.created_at,
                            department = entry.department,
                            description = entry.description,
                            expires_at = entry.expires_at,
                            id = entry.id,
                            location = entry.location,
                            short_description = entry.short_description,
                            status = entry.status,
                            title = entry.title,
                            type = entry.type,
                            user = entry.user
                        )
                    }
                    currentPage++

                    loadError.value = ""
                    isLoading.value = false
                    vacancyList.value += vacancyEntries
                }

                is Resource.Error -> {
                    loadError.value = results.message!!
                    isLoading.value = false
                }

                else -> {

                }
            }
        }
    }

    private val _loginState = MutableStateFlow<Resource<User>>(Resource.Empty())
    val loginState: StateFlow<Resource<User>> = _loginState

    fun doLogin(context: Context, email: String, password: String, device_name: String) {
        viewModelScope.launch {
            _loginState.value = Resource.Empty()
            val response = mainRepositoryImpl.login(email, password, device_name)
            val preferenceManager = PreferenceManager(context)
            if (response.isSuccessful) {
                _loginState.value = response.body()?.let { Resource.Success(data = it) }!!
                response.body()?.let {
                    if(!mainRepositoryImpl.checkUserExists(token = it.token!!)){
                        mainRepositoryImpl.saveUser(
                            UserEntity(
                                internalId = it.user.internal_id,
                                name = it.user.name!!,
                                givenName = it.user.given_name,
                                familyName = it.user.family_name,
                                contactNumber = it.user.contact_number,
                                email = it.user.email!!,
                                provinceCity = it.user.province_city,
                                profilePhotoUrl = it.user.profile_photo_url!!,
                                status = it.user.status!!,
                                applicationsCount = it.user.applications_count!!,
                                joined = it.user.joined?.human,
                                verified = it.user.verified,
                                token = it.token,
                            )
                        )
                    }
                    it.token.let { token -> preferenceManager.saveToken(token) }
                }
                loadError.value = ""
                isLoading.value = false
            } else {
                if (response.code() == 422) {
                    _loginState.value =
                        Resource.Error(context.getString(R.string.incorrect_credentials))
                    responseMessage.value = ResponseMessage(_loginState.value.message!!)
                    loadError.value = response.message()
                } else {
                    _loginState.value = Resource.Error(response.message())
                    responseMessage.value = ResponseMessage(response.message())
                    loadError.value = response.message()
                }
                isLoading.value = false
                preferenceManager.clearToken()
            }
        }
    }

    fun doGetLoggedInUser(context: Context) {
        viewModelScope.launch {
            _loginState.value = Resource.Empty()
            val response = mainRepositoryImpl.getLoggedInUser()
            if (response.isSuccessful) {
                _loginState.value = Resource.Success(response.body()!!)
                loadError.value = ""
                isLoading.value = false
                UserSession(user = _loginState.value.data?.user!!)
            } else {
                val preferenceManager = PreferenceManager(context)
                preferenceManager.clearToken()
            }

        }
    }


}