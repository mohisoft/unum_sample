package com.example.application.presentation.main.impl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.application.R
import com.example.application.databinding.ViewImageBinding
import com.example.application.domain.Image

class ImageAdapter(val listener: OnImageClickedListener) : ListAdapter<Image, ImageAdapter.PetViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK : DiffUtil.ItemCallback<Image> = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean = oldItem == newItem

        }
    }

    inner class PetViewHolder(private val binding: ViewImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Image) = with(binding) {
            Glide
                .with(binding.root)
                .load(item.urls.thumb)
                .into(image)
            binding.image.contentDescription = item.description
            binding.likes.text = binding.root.context.getString(R.string.likes_label, item.likes)
            binding.root.setOnClickListener {
                listener.onImageClicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder =
        PetViewHolder(ViewImageBinding.inflate(LayoutInflater.from(parent.context)))


    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface OnImageClickedListener {
        fun onImageClicked(image: Image)
    }
}