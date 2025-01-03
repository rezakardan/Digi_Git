package com.example.digikalax.ui.category.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.databinding.ItemcategoriesBinding
import com.example.digikalax.data.models.categories.ResponseSubCategories
import javax.inject.Inject

class DigitalAdapter @Inject constructor(): RecyclerView.Adapter<DigitalAdapter.DigitalViewHolder>() {


    lateinit var binding: ItemcategoriesBinding






    var bannerItems= emptyList<ResponseSubCategories.Data.Digital>()

    inner class DigitalViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseSubCategories.Data.Digital){


            binding.imgCategory.load(items.image)

            binding.titleCategory.text=items.name

            binding.numberCategory.text="  کالا ${items.count.toString()} + "




            binding.root.setOnClickListener {


                onItemClickListener?.let {


                    it(items.id)


                }

            }


        }


    }



    private   var onItemClickListener:((String)->Unit)?=null

    fun setOnItemClickListener(listener:(String)->Unit){


        onItemClickListener=listener



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DigitalViewHolder {
        binding= ItemcategoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DigitalViewHolder(binding.root)
    }

    override fun getItemCount()=bannerItems.size

    override fun onBindViewHolder(holder: DigitalViewHolder, position: Int) {
        return holder.onBind(bannerItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()



    fun setData(data:List<ResponseSubCategories.Data.Digital>){

        val diffUtils= DiffUtilssssssssssss(bannerItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        bannerItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilssssssssssss(val oldItem:List<ResponseSubCategories.Data.Digital>, val newItem:List<ResponseSubCategories.Data.Digital>):
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