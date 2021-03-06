package com.bipuldevashish.pro_x.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bipuldevashish.pro_x.R
import com.bipuldevashish.pro_x.data.models.Photos
import com.bipuldevashish.pro_x.databinding.ImageItemRvBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


class SearchImageAdapter(private val listner: OnitemClickListner) :
    PagingDataAdapter<Photos, SearchImageAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

    inner class PhotoViewHolder(private val binding: ImageItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
                binding.root.setOnClickListener{
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION ){
                            val item = getItem(position)
                            if (item != null){
                                listner.onItemClick(item)
                            }
                    }
                }
        }

        fun bind(photo: Photos) {
            binding.apply {

                Glide.with(itemView)
                        .load(photo.src.medium)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_round_erro_24)
                        .into(imgItem)

            }
        }
    }

    interface OnitemClickListner {
        fun onItemClick(photo: Photos)
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<Photos>() {
            override fun areItemsTheSame(
                oldItem: Photos,
                newItem: Photos
            ) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Photos,
                newItem: Photos
            ) =
                oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ImageItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }
}



