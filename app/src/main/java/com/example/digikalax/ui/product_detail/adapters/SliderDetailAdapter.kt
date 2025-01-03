package com.example.digikalax.ui.product_detail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.data.models.product_detail.ResponseGetProductById
import com.example.digikalax.databinding.ItemsliderBinding
import javax.inject.Inject

class SliderDetailAdapter @Inject constructor() : RecyclerView.Adapter<SliderDetailAdapter.SliderDetailViewHolder>() {

    lateinit var binding: ItemsliderBinding


    private var itemsShowCase = emptyList<ResponseGetProductById.Data.ImageSlider>()

    inner class SliderDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun onBind(item: ResponseGetProductById.Data.ImageSlider) {


            binding.imgSlider.load(item.image)


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderDetailViewHolder {
        binding = ItemsliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderDetailViewHolder(binding.root)
    }

    override fun getItemCount() = itemsShowCase.size
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()
    override fun onBindViewHolder(holder: SliderDetailViewHolder, position: Int) {


        holder.onBind(itemsShowCase[position])


    }


    fun setData(data: List<ResponseGetProductById.Data.ImageSlider>) {

        val diffUtiles = DiffUtils(itemsShowCase, data)
        val diff = DiffUtil.calculateDiff(diffUtiles)

        itemsShowCase = data

        diff.dispatchUpdatesTo(this)


    }


}


class DiffUtils(
    private val oldItem: List<ResponseGetProductById.Data.ImageSlider>,
    private val newItem: List<ResponseGetProductById.Data.ImageSlider>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldItem.size
    }

    override fun getNewListSize(): Int {
        return newItem.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem[oldItemPosition] === newItem[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem[oldItemPosition] === newItem[newItemPosition]
    }


}
