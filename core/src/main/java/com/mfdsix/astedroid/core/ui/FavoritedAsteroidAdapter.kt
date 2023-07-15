package com.mfdsix.astedroid.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfdsix.astedroid.core.R
import com.mfdsix.astedroid.core.databinding.ItemFavoritedAsteroidBinding
import com.mfdsix.astedroid.core.domain.model.Asteroid

class FavoritedAsteroidAdapter : ListAdapter<Asteroid, FavoritedAsteroidAdapter.MyViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Asteroid) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
            ItemFavoritedAsteroidBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class MyViewHolder(private val binding: ItemFavoritedAsteroidBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(data: Asteroid) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image ?: ContextCompat.getDrawable(itemView.context, R.drawable.asteroid))
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemDescription.text = data.description.substring(0, 50)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Asteroid> =
            object : DiffUtil.ItemCallback<Asteroid>() {
                override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
                    return oldItem == newItem
                }
            }
    }
}