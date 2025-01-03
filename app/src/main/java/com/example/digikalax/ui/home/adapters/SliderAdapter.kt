package com.example.digikalax.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.databinding.ItemsliderBinding
import com.example.digikalax.data.models.home.ResponseSliders
import com.example.digikalax.databinding.ItemSliderHomeBinding
import javax.inject.Inject

class SliderAdapter @Inject constructor() : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    lateinit var binding: ItemSliderHomeBinding


    private var itemsShowCase = emptyList<ResponseSliders.Data>()

    inner class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun onBind(item: ResponseSliders.Data) {


            binding.imgSlider.load(item.image)



            binding.root.setOnClickListener {

                onItemClickListener?.let {

                    it(item.url)



                }



            }






        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        binding = ItemSliderHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding.root)
    }

    override fun getItemCount() = itemsShowCase.size
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()
    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {


        holder.onBind(itemsShowCase[position])


    }





    private var onItemClickListener:((String)->Unit)?=null


    fun setOnItemClickListener(listener:(String)->Unit){


        onItemClickListener=listener


    }








    fun setData(data: List<ResponseSliders.Data>) {

        val diffUtiles = DiffUtils(itemsShowCase, data)
        val diff = DiffUtil.calculateDiff(diffUtiles)

        itemsShowCase = data

        diff.dispatchUpdatesTo(this)


    }


}


class DiffUtils(
    private val oldItem: List<ResponseSliders.Data>,
    private val newItem: List<ResponseSliders.Data>
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



