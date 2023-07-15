package com.mfdsix.astedroid.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfdsix.astedroid.core.R
import com.mfdsix.astedroid.core.databinding.ItemAsteroidBinding
import com.mfdsix.astedroid.core.domain.model.Asteroid

class AsteroidAdapter : ListAdapter<Asteroid, AsteroidAdapter.ViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Asteroid) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = LayoutInflater.from(parent.context).inflate(R.layout.item_asteroid ,parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemAsteroidBinding.bind(itemView)

        fun bind(data: Asteroid) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image ?: ContextCompat.getDrawable(itemView.context, R.drawable.asteroid))
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemDescription.text = data.description.take(50)
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