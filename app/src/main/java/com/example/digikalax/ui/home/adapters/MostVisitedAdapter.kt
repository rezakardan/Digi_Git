package com.example.digikalax.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.databinding.ItembestsellersBinding
import com.example.digikalax.data.models.home.ResponseBestSellerProducts
import com.example.digikalax.data.models.home.ResponseMostVisitedProducts
import javax.inject.Inject

class MostVisitedAdapter @Inject constructor(): RecyclerView.Adapter<MostVisitedAdapter.MostVisitedViewHolder>() {


    lateinit var binding: ItembestsellersBinding






    var bestSellerItems= emptyList<ResponseMostVisitedProducts.Data>()

    inner class MostVisitedViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseMostVisitedProducts.Data){


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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostVisitedViewHolder {
        binding= ItembestsellersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MostVisitedViewHolder(binding.root)
    }

    override fun getItemCount()=bestSellerItems.size

    override fun onBindViewHolder(holder: MostVisitedViewHolder, position: Int) {
        return holder.onBind(bestSellerItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()



    private var onItemClickListener:((String)->Unit)?=null

    fun setOnItemClickListener(listener:(String)->Unit){


        onItemClickListener=listener

    }





    fun setData(data:List<ResponseMostVisitedProducts.Data>){

        val diffUtils=DiffUtilsssssssss(bestSellerItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        bestSellerItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilsssssssss(val oldItem:List<ResponseMostVisitedProducts.Data>, val newItem:List<ResponseMostVisitedProducts.Data>):
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