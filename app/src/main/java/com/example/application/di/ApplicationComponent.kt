package com.example.application.di

import dagger.Component
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@Component
@DefineComponent(parent = SingletonComponent::class)
interface ApplicationComponent