package com.example.digikalax.ui.product_detail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.digikalax.data.models.product_detail.ResponseGetProductById
import com.example.digikalax.databinding.ItemColorBinding
import javax.inject.Inject

class ColorAdapter@Inject constructor() : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    lateinit var binding: ItemColorBinding


    private var itemsShowCase = emptyList<ResponseGetProductById.Data.Color>()

    inner class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun onBind(item: ResponseGetProductById.Data.Color) {

binding.txtColor.text=item.color

            binding.color.setBackgroundColor(android.graphics.Color.parseColor(item.code))



            binding.root.setOnClickListener {

                onItemClickListener?.let {
                    it(item.color)
                }
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        binding = ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorViewHolder(binding.root)
    }

    override fun getItemCount() = itemsShowCase.size
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()
    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {


        holder.onBind(itemsShowCase[position])


    }
private var onItemClickListener:((String)->Unit)?=null

    fun setOnItemClickListener(listener:(String)->Unit){

        onItemClickListener=listener



    }

    fun setData(data: List<ResponseGetProductById.Data.Color>) {

        val diffUtiles = DiffUtilss(itemsShowCase, data)
        val diff = DiffUtil.calculateDiff(diffUtiles)

        itemsShowCase = data

        diff.dispatchUpdatesTo(this)


    }


}


class DiffUtilss(
    private val oldItem: List<ResponseGetProductById.Data.Color>,
    private val newItem: List<ResponseGetProductById.Data.Color>
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