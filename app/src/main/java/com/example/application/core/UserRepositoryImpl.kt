package com.example.application.core

import com.example.application.domain.User
import com.example.application.api.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {

    private val currentUser = User(
        id = "gkoGjkJctv1f1u5MnN2R73zk7IoyNL3R9IUVDX3-_Do",
        username = "User"
    )

    override suspend fun getCurrentUser(): User = currentUser
}