package com.mfdsix.astedroid.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfdsix.astedroid.core.R
import com.mfdsix.astedroid.core.databinding.ItemAsteroidBinding
import com.mfdsix.astedroid.core.domain.model.Asteroid

class AsteroidAdapter : RecyclerView.Adapter<AsteroidAdapter.ListViewHolder>() {

    private var listData = ArrayList<Asteroid>()
    var onItemClick: ((Asteroid) -> Unit)? = null

    fun setData(newListData: List<Asteroid>?) {
        if (newListData == null) return
//        not work, dont know why
//        val diffResult = DiffUtil.calculateDiff(AsteroidDiffUtilCallback(listData, newListData))

        listData.clear()
        listData.addAll(newListData)

//          not work as well
//        notifyItemRangeInserted(0, newListData.size)
//        notifyItemRangeChanged(0, newListData.size)

//      so i am just using this instead
        notifyDataSetChanged()

//        not work, dont know why
//        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_asteroid, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemAsteroidBinding.bind(itemView)
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
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}