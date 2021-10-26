package com.example.application.api

import com.example.application.domain.Image
import com.example.application.domain.User

interface ImageRepository {
    suspend fun getImageForUser(user: User): List<Image>
}