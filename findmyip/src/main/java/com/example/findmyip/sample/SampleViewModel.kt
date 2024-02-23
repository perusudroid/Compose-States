package com.example.findmyip.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findmyip.retrofit.RetrofitInstance
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SampleViewModel(val sampleRepository: SampleRepository) : ViewModel() {

    private val _controlEventFlow = MutableSharedFlow<SampleEvent>()
    val controlEventFlow : SharedFlow<SampleEvent> get() = _controlEventFlow.asSharedFlow()

    private val _viewStateFlow = MutableStateFlow(SampleUIState(sampleViewState = SampleViewState.Init))
    val viewStateFlow : StateFlow<SampleUIState> get() = _viewStateFlow



    init {
        controlEventFlow.onEach {
            controlEvents(it)
        }.launchIn(viewModelScope)

       /*viewModelScope.launch {
           delay(1000)
           _viewStateFlow.emit(SampleUIState(sampleViewState = SampleViewState.Loading))
       }*/

        fetchAPI()

    }

    fun fetchAPI(){
        viewModelScope.launch {
            _viewStateFlow.emit(SampleUIState(sampleViewState = SampleViewState.Loading))
            val response = sampleRepository.fetchAPI()
            if(response.code() == 200)
                _viewStateFlow.emit(SampleUIState(sampleViewState = SampleViewState.Success(response.body())))
            else
                _viewStateFlow.emit(SampleUIState(sampleViewState = SampleViewState.Error(response.message())))
        }
    }

    fun emitControlEvent(sampleEvent: SampleEvent) =
        viewModelScope.launch {
            _controlEventFlow.emit(sampleEvent)
        }


    private fun controlEvents(event: SampleEvent) {
        when(event){
            is SampleEvent.onBackPressed -> {
                _viewStateFlow.update {
                    it.copy(backPress = true)
                }
            }
            is SampleEvent.onNextPressed -> {
                _viewStateFlow.update {
                    it.copy(showNext = true)
                }
            }
        }
    }

}