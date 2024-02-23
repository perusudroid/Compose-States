package com.example.findmyip.retrofit

import com.example.findmyip.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("json")
    suspend fun getUserResponse(): Response<UserResponse>
}