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

class MobileAdapter @Inject constructor(): RecyclerView.Adapter<MobileAdapter.MobileViewHolder>() {


    lateinit var binding: ItemcategoriesBinding






    var bannerItems= emptyList<ResponseSubCategories.Data.Mobile>()

    inner class MobileViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseSubCategories.Data.Mobile){


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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobileViewHolder {
        binding= ItemcategoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MobileViewHolder(binding.root)
    }

    override fun getItemCount()=bannerItems.size

    override fun onBindViewHolder(holder: MobileViewHolder, position: Int) {
        return holder.onBind(bannerItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()



    fun setData(data:List<ResponseSubCategories.Data.Mobile>){

        val diffUtils= DiffUtilsssssssssssss(bannerItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        bannerItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilsssssssssssss(val oldItem:List<ResponseSubCategories.Data.Mobile>, val newItem:List<ResponseSubCategories.Data.Mobile>):
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