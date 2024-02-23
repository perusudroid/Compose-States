package com.example.findmyip

import android.util.Log
import com.example.findmyip.model.UserResponse
import com.example.findmyip.sample.SampleRepository
import com.example.findmyip.sample.SampleViewModel
import com.example.findmyip.sample.SampleViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.asCoroutineDispatcher
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class SampleViewModelTest{

    lateinit var sampleViewModel: SampleViewModel

    val mainThread = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)

    }

    @After
    fun tearDown() {

    }

    @Test
    fun testAPI(){
        val sampleRepo = mockk<SampleRepository>()
        coEvery {
            sampleRepo.fetchAPI()
        } returns Response.success(UserResponse())
        sampleViewModel = SampleViewModel(sampleRepo)
        sampleViewModel.fetchAPI()
        val result = sampleViewModel.viewStateFlow.value
        Log.d("SampleViewModelTest", "testAPI: result $result")
        //Assert.assertEquals(1, result.sampleViewState)
    }
}