package com.example.digikalax.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.databinding.ItembannersBinding
import com.example.digikalax.databinding.ItemcategoryBinding
import com.example.digikalax.data.models.home.ResponseGetBanners
import com.example.digikalax.data.models.home.ResponseGetCategories
import javax.inject.Inject

class CategoryAdapter@Inject constructor(): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    lateinit var binding: ItemcategoryBinding






    var bannerItems= emptyList<ResponseGetCategories.Data>()

    inner class CategoryViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseGetCategories.Data){


            binding.imgCategory.load(items.image)

            binding.txtCategory.text=items.name


binding.root.setOnClickListener {


    onItemClickListener?.let {


        it(items.id)


    }


}


        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        binding= ItemcategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(binding.root)
    }

    override fun getItemCount()=bannerItems.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        return holder.onBind(bannerItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()




    private var onItemClickListener:((String)->Unit)?=null

    fun setOnItemClickListener(listener:(String)->Unit){

        onItemClickListener=listener
    }




    fun setData(data:List<ResponseGetCategories.Data>){

        val diffUtils=DiffUtilsssssssssssss(bannerItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        bannerItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilsssssssssssss(val oldItem:List<ResponseGetCategories.Data>, val newItem:List<ResponseGetCategories.Data>):
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