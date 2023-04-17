package dev.dicesystems.varsitutor.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dicesystems.varsitutor.data.models.VacancyModel
import dev.dicesystems.varsitutor.repository.VacancyRepository
import dev.dicesystems.varsitutor.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VacancyListViewModel @Inject constructor(
    private val repository: VacancyRepository
) : ViewModel() {

    private var currentPage = 1

    var vacancyList = mutableStateOf<List<VacancyModel>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadVacancyPaginatedList()
    }

    fun loadVacancyPaginatedList() {
        viewModelScope.launch {

            isLoading.value = true
            when (val results = repository.getVacancyList(currentPage)) {
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
}