package com.example.findmyip.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SampleViewModel : ViewModel() {

    private val sampleRepository: SampleRepository by lazy { SampleRepository() }

    private val _viewStateFlow = MutableStateFlow(SampleUIState(sampleViewState = SampleViewState.Init))
    val viewStateFlow : StateFlow<SampleUIState> get() = _viewStateFlow


    init {
        fetchAPI()
    }

    private fun fetchAPI(){
        viewModelScope.launch {
            _viewStateFlow.emit(SampleUIState(sampleViewState = SampleViewState.Loading))
            val response = sampleRepository.fetchAPI()
            if(response.code() == 200)
                _viewStateFlow.emit(SampleUIState(sampleViewState = SampleViewState.Success(response.body())))
            else
                _viewStateFlow.emit(SampleUIState(sampleViewState = SampleViewState.Error(response.message())))
        }
    }

}