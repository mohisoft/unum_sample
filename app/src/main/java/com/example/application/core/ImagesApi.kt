package com.example.application.core

import com.example.application.domain.Image
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApi {
    @GET("photos")
    suspend fun queryImages(@Query("client_id") clientToken: String): List<Image>
}