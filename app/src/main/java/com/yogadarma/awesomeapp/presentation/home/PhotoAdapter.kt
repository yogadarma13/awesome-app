package com.yogadarma.awesomeapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yogadarma.awesomeapp.databinding.ItemGridBinding
import com.yogadarma.awesomeapp.databinding.ItemListBinding
import com.yogadarma.awesomeapp.utils.loadImageRounded
import com.yogadarma.core.domain.model.Photo

class PhotoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listData = ArrayList<Photo>()
    private var isList = true

    var onItemClick: ((Int) -> Unit)? = null

    fun setData(newListData: List<Photo>?, isList: Boolean) {
        this.isList = isList
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (isList) {
            ListViewHolder(
                ItemListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            GridViewHolder(
                ItemGridBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isList) (holder as ListViewHolder).bind(listData[position])
        else (holder as GridViewHolder).bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Photo) {
            with(binding) {
                imgItem.loadImageRounded(data.photoUrl)
                tvPhotographer.text = data.photographer
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition].id)
            }
        }
    }

    inner class GridViewHolder(private val binding: ItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Photo) {
            with(binding) {
                imgItem.loadImageRounded(data.photoUrl)
                tvPhotographer.text = data.photographer
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition].id)
            }
        }
    }
}