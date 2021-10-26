package com.example.application.api

import com.example.application.domain.User

interface UserRepository {
    suspend fun getCurrentUser(): User
}