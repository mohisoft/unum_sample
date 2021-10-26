package com.example.application.presentation.main.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.application.presentation.main.MainViewModel
import com.example.application.domain.Image
import com.example.application.api.ImageRepository
import com.example.application.domain.User
import com.example.application.api.UserRepository
import com.example.application.util.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val imageRepository: ImageRepository,
    private val userRepository: UserRepository
): MainViewModel() {

    override val user = MutableLiveData<User>()

    override val thumbnails = MutableLiveData<List<Image>>().apply {
        value = emptyList()
    }

    private val _state = MutableStateFlow<Action>(
        Action.ShowLoader(false)
    )
    override val state: Flow<Action> = _state

    override fun refresh() {
        _state.value = Action.ShowLoader(true)
        fetchUser()
    }

    override fun onImageClicked(image: Image) {
        _state.value = Action.ShowPreview(image)
    }

    private fun fetchUser() = viewModelScope.launch(Dispatchers.IO) {
        Logger.d("fetchUser")
        val currentUser = userRepository.getCurrentUser()
        viewModelScope.launch {
            user.value = currentUser
        }
        fetchImages(currentUser)
    }

    private fun fetchImages(user: User) {
        Logger.d("fetchImages")
        viewModelScope.launch(Dispatchers.IO) {
            val pictures = imageRepository.getImageForUser(user)

            viewModelScope.launch {
                thumbnails.value = pictures
                _state.value = Action.ShowLoader(false)
            }
        }
    }
}