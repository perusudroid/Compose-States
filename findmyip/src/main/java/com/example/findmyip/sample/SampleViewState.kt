package com.example.findmyip.sample

import com.example.findmyip.model.UserResponse


data class SampleUIState(
    val sampleViewState: SampleViewState,
    val showPrevious : Boolean = false,
    val showNext : Boolean = false,
    val backPress : Boolean = false,
)

sealed class SampleViewState{
    object Init : SampleViewState()
    object Loading : SampleViewState()
    data class Success(val data : UserResponse?) : SampleViewState()
    data class Error(val data : String?) : SampleViewState()
}