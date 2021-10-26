package com.example.application.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.example.application.R
import com.example.application.domain.Image
import com.example.application.presentation.main.MainViewModel
import com.example.application.presentation.main.base.MainViewModelImpl
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ImageDialogFragment() : BottomSheetDialogFragment(), LifecycleObserver {

    private lateinit var viewModel: MainViewModelImpl

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModelImpl::class.java)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                when(state) {
                    is MainViewModel.Action.ShowPreview -> showPreview(view, view.findViewById(R.id.image), state.image)
                    else -> {
                        // Do nothing
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_preview, container, false)
    }

    private fun showPreview(rootView: View, imageView: ImageView, image: Image) {
        Glide
            .with(rootView)
            .load(image.urls.regular)
            .into(imageView)
    }

    companion object {
        const val TAG = "ImageDialogFragment"
        private const val STATE = "DialogState"
    }
}