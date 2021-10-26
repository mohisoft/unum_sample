package com.example.application.di

import com.example.application.api.ImageRepository
import com.example.application.api.UserRepository
import com.example.application.core.ImageRepositoryImpl
import com.example.application.core.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsImageRepository(repository: ImageRepositoryImpl): ImageRepository

    @Binds
    abstract fun bindsUserRepository(repository: UserRepositoryImpl): UserRepository
}