package com.yogadarma.awesomeapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yogadarma.awesomeapp.databinding.ItemListBinding
import com.yogadarma.awesomeapp.utils.loadImageRounded
import com.yogadarma.core.domain.model.Photo

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    private var listData = ArrayList<Photo>()

    fun setData(newListData: List<Photo>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Photo) {
            with(binding) {
                imgItem.loadImageRounded(data.photoUrl)
                tvPhotographer.text = data.photographer
            }
        }
    }
}