package com.example.application.presentation.main.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.application.R
import com.example.application.base.impl.BaseFragment
import com.example.application.databinding.FragmentMainScreenBinding
import com.example.application.domain.Image
import com.example.application.presentation.common.ImageDialogFragment
import com.example.application.presentation.main.MainViewModel
import com.example.application.presentation.main.impl.ImageAdapter
import com.example.application.presentation.main.impl.ImageDecorator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainFragment: BaseFragment<FragmentMainScreenBinding>(), ImageAdapter.OnImageClickedListener {

    private lateinit var viewModel: MainViewModelImpl

    private val adapter = ImageAdapter(this)

    override val bindingInflater: (LayoutInflater) -> FragmentMainScreenBinding = { inflater ->
        FragmentMainScreenBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
    }

    private fun bindViews() {
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModelImpl::class.java)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            with(binding) {
                context?.let {
                    val columnCount = resources.getInteger(R.integer.image_grid_column_count)
                    imagesRecyclerView.adapter = adapter
                    imagesRecyclerView.layoutManager = GridLayoutManager(context, columnCount)
                    imagesRecyclerView.addItemDecoration(ImageDecorator(it, columnCount))

                    swipeLayout.setOnRefreshListener {
                        viewModel.refresh()
                    }

                    viewModel.thumbnails.observe(viewLifecycleOwner) { pictures ->
                        adapter.submitList(pictures)
                    }

                    viewModel.refresh()
                } ?: error("Context might not be null at this point!")
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.state.collect { action ->
                when(action) {
                    is MainViewModel.Action.ShowLoader -> showLoader(action.show)
                    is MainViewModel.Action.ShowPreview -> showPreview(action.image)
                }
            }
        }
    }

    private fun showPreview(image: Image) {
        ImageDialogFragment().show(childFragmentManager, ImageDialogFragment.TAG)
    }

    private fun showLoader(show: Boolean) = with(binding) {
        swipeLayout.isRefreshing = show
    }

    override fun onImageClicked(image: Image) =
        viewModel.onImageClicked(image)

}