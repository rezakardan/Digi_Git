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

class SuperMarketAdapter @Inject constructor(): RecyclerView.Adapter<SuperMarketAdapter.SuperMarketsViewHolder>() {


    lateinit var binding: ItemcategoriesBinding






    var superItems= emptyList<ResponseSubCategories.Data.Supermarket>()

    inner class SuperMarketsViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseSubCategories.Data.Supermarket){


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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperMarketsViewHolder {
        binding= ItemcategoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SuperMarketsViewHolder(binding.root)
    }

    override fun getItemCount()=superItems.size

    override fun onBindViewHolder(holder: SuperMarketsViewHolder, position: Int) {
        return holder.onBind(superItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()



    fun setData(data:List<ResponseSubCategories.Data.Supermarket>){

        val diffUtils= DiffUtilsssssssssssssssss(superItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        superItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilsssssssssssssssss(val oldItem:List<ResponseSubCategories.Data.Supermarket>, val newItem:List<ResponseSubCategories.Data.Supermarket>):
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