package com.example.application

import com.example.application.presentation.main.base.MainViewModelImpl
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelTest {

    @RelaxedMockK
    lateinit var viewModel: MainViewModelImpl

    @Test
    fun viewModel() {

        viewModel.fetchImages()

        // Await

        assertEquals(5, viewModel.thumbnails.value?.size ?: 0)
    }
}