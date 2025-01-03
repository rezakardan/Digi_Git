package com.example.digikalax.ui.home.adapters

import android.graphics.Paint
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.R
import com.example.digikalax.databinding.ItemAmazingBinding
import com.example.digikalax.databinding.ItembestsellersBinding
import com.example.digikalax.data.models.home.ResponseBestSellerProducts
import com.example.digikalax.data.models.home.ResponseGetAmazingProducts
import javax.inject.Inject

class BestSellerAdapter@Inject constructor(): RecyclerView.Adapter<BestSellerAdapter.BestSellerViewHolder>() {


    lateinit var binding: ItembestsellersBinding






    var bestSellerItems= emptyList<ResponseBestSellerProducts.Data>()

    inner class BestSellerViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseBestSellerProducts.Data){


binding.imgBestSeller.load(items.image)

            binding.numberBestSeller.text="${adapterPosition+1}"

            binding.txtBestSeller.text=items.name



            binding.root.setOnClickListener {


                onItemClickListener?.let {
                    it(items.id)
                }
            }


        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        binding= ItembestsellersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BestSellerViewHolder(binding.root)
    }

    override fun getItemCount()=bestSellerItems.size

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        return holder.onBind(bestSellerItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()

private var onItemClickListener:((String)->Unit)?=null

    fun setOnItemClickListener(listener:(String)->Unit){


        onItemClickListener=listener

    }

    fun setData(data:List<ResponseBestSellerProducts.Data>){

        val diffUtils=DiffUtilsssssss(bestSellerItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        bestSellerItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilsssssss(val oldItem:List<ResponseBestSellerProducts.Data>, val newItem:List<ResponseBestSellerProducts.Data>):
    DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldItem.size
    }

    override fun getNewListSize(): Int {
        return newItem.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem[oldItemPosition]===newItem[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem[oldItemPosition]===newItem[newItemPosition]
    }


}