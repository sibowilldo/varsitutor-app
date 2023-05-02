package dev.dicesystems.varsitutor.viewmodels

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dicesystems.varsitutor.R
import dev.dicesystems.varsitutor.UriPathFinder
import dev.dicesystems.varsitutor.data.local.entities.UserEntity
import dev.dicesystems.varsitutor.data.models.ApplicationModel
import dev.dicesystems.varsitutor.data.models.CreateApplicationModel
import dev.dicesystems.varsitutor.data.models.FavoriteModel
import dev.dicesystems.varsitutor.data.models.LoginModel
import dev.dicesystems.varsitutor.data.models.NotificationModel
import dev.dicesystems.varsitutor.data.models.RegisterModel
import dev.dicesystems.varsitutor.data.models.VacancyModel
import dev.dicesystems.varsitutor.data.remote.responses.Application
import dev.dicesystems.varsitutor.data.remote.responses.Favorites
import dev.dicesystems.varsitutor.data.remote.responses.ResponseMessage
import dev.dicesystems.varsitutor.data.remote.responses.User
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
    var notificationList = mutableStateOf<List<NotificationModel>>(listOf())
    var applicationsList = mutableStateOf<List<ApplicationModel>>(listOf())
    var favoritesList = mutableStateOf<List<FavoriteModel>>(listOf())
    var responseMessage = mutableStateOf(ResponseMessage(""))
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadVacancyPaginatedList()
        loadNotifications()
        loadFavorites()
        loadApplications()
    }

    fun refreshNotificationList() {
        notificationList = mutableStateOf(listOf())
        loadNotifications()

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


    fun loadNotifications() {
        viewModelScope.launch {
            isLoading.value = true
            when (val results = mainRepositoryImpl.getNotificationList()) {
                is Resource.Success -> {
                    val notificationEntries = results.data!!.notifications.mapIndexed { _, entry ->
                        NotificationModel(
                            id = entry.id,
                            title = entry.title,
                            body = entry.body,
                            created_at = entry.created_at,
                            read_at = entry.read_at,
                            updated_at = entry.updated_at,
                        )
                    }
                    currentPage++

                    loadError.value = ""
                    isLoading.value = false
                    notificationList.value += notificationEntries
                }

                is Resource.Error -> {
                    loadError.value = results.message!!
                    isLoading.value = false
                }

                else -> {
                    loadError.value = results.message!!
                    isLoading.value = false
                }
            }
        }
    }


    fun loadApplications() {
        viewModelScope.launch {
            isLoading.value = true
            when (val results = mainRepositoryImpl.getApplicationsList()) {
                is Resource.Success -> {
                    val applicationEntries = results.data!!.applications.mapIndexed { _, entry ->
                        ApplicationModel(
                            additional_information =  entry.additional_information,
                            attachments = entry.attachments,
                            contact_number =  entry.contact_number,
                            created_at =  entry.created_at,
                            email =  entry.email,
                            id =  entry.id,
                            status =  entry.status,
                            vacancy =  entry.vacancy
                        )
                    }
                    currentPage++

                    loadError.value = ""
                    isLoading.value = false
                    applicationsList.value += applicationEntries
                }

                is Resource.Error -> {
                    loadError.value = results.message!!
                    isLoading.value = false
                }

                else -> {
                    loadError.value = results.message!!
                    isLoading.value = false
                }
            }
        }
    }


    private val _favoriteState = MutableStateFlow<List<FavoriteModel>?>(null)
    val favoriteState: StateFlow<List<FavoriteModel>?> = _favoriteState
    fun loadFavorites() {
        viewModelScope.launch {
            isLoading.value = true
            when (val results = mainRepositoryImpl.getFavoritesList()) {
                is Resource.Success -> {
                    val favoriteEntries = results.data!!.favorites.mapIndexed { _, entry ->
                        FavoriteModel(
                            id = entry.id,
                            created_at = entry.created_at,
                            vacancy = entry.vacancy
                        )
                    }
                    currentPage++

                    loadError.value = ""
                    isLoading.value = false
                    favoritesList.value += favoriteEntries
                }

                is Resource.Error -> {
                    Log.d(TAG, "loadFavorites: Called ${results.message}")
                    loadError.value = results.message!!
                    isLoading.value = false
                }

                else -> {
                    loadError.value = results.message!!
                    isLoading.value = false
                }
            }
        }
    }


    private val _loginState = MutableStateFlow<Resource<User>>(Resource.Empty())
    val loginState: StateFlow<Resource<User>> = _loginState

    fun doLogin(context: Context, loginModel: LoginModel) {
        viewModelScope.launch {
            _loginState.value = Resource.Empty()
            val response = mainRepositoryImpl.login(loginModel)
            val preferenceManager = PreferenceManager(context)
            if (response.isSuccessful) {
                _loginState.value = response.body()?.let { Resource.Success(data = it) }!!
                response.body()?.let {
                    if (!mainRepositoryImpl.checkUserExists(token = it.token!!)) {
                        mainRepositoryImpl.saveUser(saveUserToDB(it))
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
            } else {
                val preferenceManager = PreferenceManager(context)
                preferenceManager.clearToken()
            }

        }
    }

    fun doRegister(context: Context, registerModel: RegisterModel) {
        viewModelScope.launch {
            _loginState.value = Resource.Empty()
            val response = mainRepositoryImpl.register(registerModel)
            val preferenceManager = PreferenceManager(context)
            if (response.isSuccessful) {
                _loginState.value = response.body()?.let { Resource.Success(data = it) }!!
                response.body()?.let {
                    if (!mainRepositoryImpl.checkUserExists(token = it.token!!)) {
                        mainRepositoryImpl.saveUser(saveUserToDB(it))
                    }
                    it.token.let { token -> preferenceManager.saveToken(token) }
                }
                loadError.value = ""
                isLoading.value = false
            } else {
                if (response.code() == 422) {

                    _loginState.value =
                        Resource.Error("Invalid information Provided")
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


    private val _createApplicationState = MutableStateFlow<Resource<Application>>(Resource.Empty())
    val createApplicationState: StateFlow<Resource<Application>> = _createApplicationState
    fun doCreateApplication(context: Context, createApplicationModel: CreateApplicationModel) {
        viewModelScope.launch {
            _createApplicationState.value = Resource.Empty()
            when (val response = mainRepositoryImpl.createApplication(createApplicationModel)) {
                is Resource.Success -> {
                    loadError.value = ""
                    isLoading.value = false
                    Toast.makeText(context, "${response.data?.message}", Toast.LENGTH_SHORT).show()
                }

                is Resource.Error -> {
                    Toast.makeText(context, "${response.data?.message}", Toast.LENGTH_SHORT).show()
                    isLoading.value = false
                    loadError.value = response.data?.message!!
                }

                else -> {
                    Toast.makeText(context, "${response.data?.message}", Toast.LENGTH_SHORT).show()
                    loadError.value = response.data?.message!!
                    isLoading.value = false
                }
            }
            isLoading.value = false
        }
    }


    private fun saveUserToDB(user: User): UserEntity {
        return UserEntity(
            id = user.user.id,
            internalId = user.user.internal_id,
            name = user.user.name!!,
            givenName = user.user.given_name,
            familyName = user.user.family_name,
            contactNumber = user.user.contact_number,
            email = user.user.email!!,
            provinceCity = user.user.province_city,
            profilePhotoUrl = user.user.profile_photo_url!!,
            status = user.user.status!!,
            applicationsCount = user.user.applications_count!!,
            joined = user.user.joined?.human,
            verified = user.user.verified,
            token = user.token!!,
        )
    }

    var state by mutableStateOf(MainScreenState())
        private set

    private val uriPathFinder = UriPathFinder()

    fun onFilePathsListChange(list: List<Uri>, context: Context) {
        val updatedList = state.filePaths.toMutableList()
        val paths = changeUriToPath(list, context)
        viewModelScope.launch {
            updatedList += paths
            state = state.copy(
                filePaths = updatedList
            )
        }
    }

    private fun changeUriToPath(uris: List<Uri>, context: Context): List<String> {
        val pathsList: MutableList<String> = mutableListOf()
        uris.forEach {
            val path = uriPathFinder.getPath(context, it)
            pathsList.add(path!!)
        }
        return pathsList
    }

}

data class MainScreenState(
    val filePaths: List<String> = emptyList()
)