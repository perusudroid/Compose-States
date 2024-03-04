package com.example.findmyip.sample

import com.example.findmyip.model.UserResponse


data class SampleUIState(
    val sampleViewState: SampleViewState
)

sealed class SampleViewState{
    object Init : SampleViewState()
    object Loading : SampleViewState()
    data class Success(val data : UserResponse?) : SampleViewState()
    data class Error(val data : String?) : SampleViewState()
}