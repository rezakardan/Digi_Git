package com.example.digikalax.ui.mydigikala.adapters

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.data.models.order.ResponseGetUserOrders
import com.example.digikalax.databinding.ItemImageBinding
import javax.inject.Inject

class ImagesAdapter@Inject constructor():RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder>() {



    lateinit var binding: ItemImageBinding


    private val decimalFormatter by lazy { DecimalFormat("##,###.##") }


    var amazingItems = emptyList<ResponseGetUserOrders.Data.OrderProduct>()

    inner class ImagesViewHolder(items: View) : RecyclerView.ViewHolder(items) {


        fun onBind(items: ResponseGetUserOrders.Data.OrderProduct) {



binding.image.load(items.image)










        }


    }










    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(binding.root)
    }

    override fun getItemCount() = amazingItems.size

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        return holder.onBind(amazingItems[position])
    }

    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()






    private var onItemClickListener:((String,String)->Unit)?= null

    fun setOnItemClickListener(listener:(String,String)->Unit){


        onItemClickListener=listener


    }


    fun setDataImages(data: List<ResponseGetUserOrders.Data.OrderProduct>) {

        val diffUtils = DiffUtilssssssss(amazingItems, data)
        val diff = DiffUtil.calculateDiff(diffUtils)
        amazingItems = data
        diff.dispatchUpdatesTo(this)


    }


}


class DiffUtilssssssss(val oldItem: List<ResponseGetUserOrders.Data.OrderProduct>, val newItem: List<ResponseGetUserOrders.Data.OrderProduct>) :
    DiffUtil.Callback() {
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