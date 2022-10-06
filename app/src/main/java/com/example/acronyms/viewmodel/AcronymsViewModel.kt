package com.example.acronyms.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acronyms.data.api.AcronymRepository
import com.example.acronyms.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AcronymsViewModel @Inject constructor(
    private val repository: AcronymRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _stateLiveData = MutableLiveData<ApiState>()
    val stateLiveData: LiveData<ApiState> get() = _stateLiveData

    fun fetchAbbreviation(sf: String) {
        viewModelScope.launch(dispatcher) {
            repository.fetchAbbreviation(sf).collect {
                _stateLiveData.postValue(it)
            }
        }
    }
}
