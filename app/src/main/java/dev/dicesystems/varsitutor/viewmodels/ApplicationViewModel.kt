package dev.dicesystems.varsitutor.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dicesystems.varsitutor.data.models.ApplicationModel
import dev.dicesystems.varsitutor.repository.ApplicationRepository
import dev.dicesystems.varsitutor.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplicationViewModel @Inject constructor(
    private val repository: ApplicationRepository
) : ViewModel() {

    private var currentPage = 1

    var applicaionList = mutableStateOf<List<ApplicationModel>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadVacancyPaginatedList()
    }

    fun loadVacancyPaginatedList() {
        viewModelScope.launch {

            isLoading.value = true
            when (val results = repository.getApplicationList(currentPage)) {
                is Resource.Success -> {

                    endReached.value = currentPage >= 5// results.data!!.meta?.last_page!!
                    val applicationEntries = results.data!!.data.mapIndexed { _, entry ->
                        ApplicationModel(
                            vacancy_id =entry.vacancy_id,
                            user_id = entry.user_id,
                            contact_number = entry.contact_number,
                            email = entry.email,
                            job_title=entry.job_title,
                            duration=entry.duration,
                            company_department=entry.company_department,
                            motivation=entry.motivation,
                            attachment=entry.attachment,
                        )
                    }
                    currentPage++

                    loadError.value = ""
                    isLoading.value = false
                    applicaionList.value += applicationEntries
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