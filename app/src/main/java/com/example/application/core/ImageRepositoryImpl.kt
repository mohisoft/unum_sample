package com.example.application.core

import com.example.application.domain.Image
import com.example.application.api.ImageRepository
import com.example.application.domain.User
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor() : ImageRepository {

    private val api: ImagesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        api = retrofit.create(ImagesApi::class.java)
    }

    override suspend fun getImageForUser(user: User): List<Image> = api.queryImages(user.id)
}