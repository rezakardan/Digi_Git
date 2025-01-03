package com.example.digikalax.ui.checkout.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.data.db.basket.CartEntity
import com.example.digikalax.databinding.ItemSendTimeBinding
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MarsoleAdapter @Inject constructor(@ApplicationContext val context: Context) :
    RecyclerView.Adapter<MarsoleAdapter.MarsoleViewHolder>() {


    lateinit var binding: ItemSendTimeBinding


    var amazingItems = emptyList<CartEntity>()

    inner class MarsoleViewHolder(items: View) : RecyclerView.ViewHolder(items) {


        fun onBind(items: CartEntity) {

            binding.imgMarsole.load(items.image)

            binding.marsoleCount.text = items.count.toString()


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsoleViewHolder {
        binding = ItemSendTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarsoleViewHolder(binding.root)
    }

    override fun getItemCount() = amazingItems.size

    override fun onBindViewHolder(holder: MarsoleViewHolder, position: Int) {
        return holder.onBind(amazingItems[position])
    }

    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()


    fun setData(data: List<CartEntity>) {

        val diffUtils = DiffUtilsssssss(amazingItems, data)
        val diff = DiffUtil.calculateDiff(diffUtils)
        amazingItems = data
        diff.dispatchUpdatesTo(this)


    }


}


class DiffUtilsssssss(val oldItem: List<CartEntity>, val newItem: List<CartEntity>) :
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