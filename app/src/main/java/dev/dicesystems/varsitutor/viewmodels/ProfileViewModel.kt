package dev.dicesystems.varsitutor.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dicesystems.varsitutor.data.local.entities.UserEntity
import dev.dicesystems.varsitutor.repository.MainRepositoryImpl
import dev.dicesystems.varsitutor.util.PreferenceManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val mainRepositoryImpl: MainRepositoryImpl
) : ViewModel() {

    private val _userState = MutableStateFlow<UserEntity?>(null)
    val userState: StateFlow<UserEntity?> = _userState

    fun getUserInfo(context: Context) {
        viewModelScope.launch {
            val preferenceManager = PreferenceManager(context)
            _userState.value =
                mainRepositoryImpl.getLoggedInUserByToken(token = preferenceManager.getToken()!!)
        }
    }
}