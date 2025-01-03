package com.example.digikalax.ui.category.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.data.models.categories.ResponseGetProductByCategory
import com.example.digikalax.databinding.ItemcategoriesBinding
import javax.inject.Inject

class ProductByCategoryAdapter @Inject constructor(): RecyclerView.Adapter<ProductByCategoryAdapter.ProductByCategoryViewHolder>() {


    lateinit var binding: ItemcategoriesBinding






    var beautyItems= emptyList<ResponseGetProductByCategory.Data>()

    inner class ProductByCategoryViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseGetProductByCategory.Data){


            binding.imgCategory.load(items.image)

            binding.titleCategory.text=items.name







        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductByCategoryViewHolder {
        binding= ItemcategoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductByCategoryViewHolder(binding.root)
    }

    override fun getItemCount()=beautyItems.size

    override fun onBindViewHolder(holder: ProductByCategoryViewHolder, position: Int) {
        return holder.onBind(beautyItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()



    fun setData(data:List<ResponseGetProductByCategory.Data>){

        val diffUtils= DiffUtilssssssssssssssssssssss(beautyItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        beautyItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilssssssssssssssssssssss(val oldItem:List<ResponseGetProductByCategory.Data>, val newItem:List<ResponseGetProductByCategory.Data>):
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