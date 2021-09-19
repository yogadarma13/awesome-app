package com.yogadarma.awesomeapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.yogadarma.awesomeapp.databinding.ItemCoverImageBinding
import com.yogadarma.awesomeapp.utils.loadImage
import com.yogadarma.core.domain.model.Photo

class CoverImageAdapter : RecyclerView.Adapter<CoverImageAdapter.ViewHolder>() {

    private var listItem = ArrayList<Photo>()
    private lateinit var viewPager: ViewPager2

    var onItemClick: ((Int) -> Unit)? = null

    private val sliderRunnable = Runnable {
        listItem.addAll(listItem)
        notifyDataSetChanged()
    }

    fun setData(newListItem: List<Photo>?, viewPager: ViewPager2) {
        if (newListItem == null) return
        listItem.clear()
        listItem.addAll(newListItem)
        this.viewPager = viewPager
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemCoverImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItem[position])

        if (position == listItem.size - 2) {
            viewPager.post(sliderRunnable)
        }
    }

    override fun getItemCount(): Int = listItem.size

    inner class ViewHolder(private val binding: ItemCoverImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Photo) {
            binding.imgCover.loadImage(data.photoUrl)
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listItem[adapterPosition].id)
            }
        }
    }
}