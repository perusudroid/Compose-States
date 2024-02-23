package com.example.findmyip.sample

import com.example.findmyip.retrofit.RetrofitInstance

class SampleRepository {

    private val apiService by lazy { RetrofitInstance.api }

    suspend fun fetchAPI() = apiService.getUserResponse()

}