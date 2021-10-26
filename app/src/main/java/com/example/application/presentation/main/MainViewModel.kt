package com.example.application.presentation.main

import androidx.lifecycle.LiveData
import com.example.application.base.BaseViewModel
import com.example.application.domain.Image
import com.example.application.domain.User
import kotlinx.coroutines.flow.Flow

abstract class MainViewModel : BaseViewModel() {
    abstract val user: LiveData<User>

    abstract val thumbnails: LiveData<List<Image>>

    abstract val state: Flow<Action>

    abstract fun refresh()

    abstract fun onImageClicked(image: Image)

    sealed class Action {
        data class ShowLoader(val show: Boolean) : Action()
        data class ShowPreview(val image: Image) : Action()
    }
}