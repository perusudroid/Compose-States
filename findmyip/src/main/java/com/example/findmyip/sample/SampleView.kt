package com.example.findmyip.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SampleView(
    uiState: SampleUIState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            when (uiState.sampleViewState) {
                SampleViewState.Init -> {}
                SampleViewState.Loading -> Text(text = "Loading...")
                is SampleViewState.Success -> Text(text = "Content Loaded...\n ${uiState.sampleViewState.data}")
                is SampleViewState.Error -> Text(text = "Error ${uiState.sampleViewState.data}")
            }
        }
    }

}