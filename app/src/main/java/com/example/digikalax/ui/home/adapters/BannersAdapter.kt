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
import com.example.digikalax.databinding.ItembannersBinding
import com.example.digikalax.data.models.home.ResponseGetAmazingProducts
import com.example.digikalax.data.models.home.ResponseGetBanners
import javax.inject.Inject

class BannersAdapter@Inject constructor(): RecyclerView.Adapter<BannersAdapter.BannersViewHolder>() {


    lateinit var binding: ItembannersBinding






    var bannerItems= emptyList<ResponseGetBanners.Data>()

    inner class BannersViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseGetBanners.Data){


binding.imgBanners.load(items.image)



binding.root.setOnClickListener {

    onItemClickListener?.let {

        it(items.url)
    }


}

        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersViewHolder {
        binding= ItembannersBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BannersViewHolder(binding.root)
    }

    override fun getItemCount()=bannerItems.size

    override fun onBindViewHolder(holder: BannersViewHolder, position: Int) {
        return holder.onBind(bannerItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()



    private var onItemClickListener:((String)->Unit)?=null

    fun setOnItemClickListener(listener:(String)->Unit){
        onItemClickListener=listener
    }


    fun setData(data:List<ResponseGetBanners.Data>){

        val diffUtils=DiffUtilsssssssssss(bannerItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        bannerItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilsssssssssss(val oldItem:List<ResponseGetBanners.Data>, val newItem:List<ResponseGetBanners.Data>):
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