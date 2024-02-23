package com.example.findmyip.sample

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Composable
fun SampleView(
    uiState: SampleUIState,
    sampleEvent: (SampleEvent) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Button(onClick = { sampleEvent(SampleEvent.onNextPressed("sample")) }) {
                Text(text = "Next")
            }

            Button(onClick = { sampleEvent(SampleEvent.onBackPressed) }) {
                Text(text = "Previous")
            }

            if (uiState.backPress) {
                Toast.makeText(LocalContext.current, "Back press clicked", Toast.LENGTH_SHORT)
                    .show()
            }

            when (uiState.sampleViewState) {
                SampleViewState.Init -> {}
                SampleViewState.Loading ->  Text(text = "Loading...")
                is SampleViewState.Success -> Text(text = "Content Loaded...\n ${uiState.sampleViewState.data}")
                is SampleViewState.Error -> Text(text = "Error ${uiState.sampleViewState.data}")
            }
        }
    }

}