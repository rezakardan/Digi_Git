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

class HomeAdapter @Inject constructor(): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {


    lateinit var binding: ItemcategoriesBinding






    var homeItems= emptyList<ResponseSubCategories.Data.Home>()

    inner class HomeViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseSubCategories.Data.Home){


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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding= ItemcategoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(binding.root)
    }

    override fun getItemCount()=homeItems.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        return holder.onBind(homeItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()



    fun setData(data:List<ResponseSubCategories.Data.Home>){

        val diffUtils= DiffUtilssssssssssssssss(homeItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        homeItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilssssssssssssssss(val oldItem:List<ResponseSubCategories.Data.Home>, val newItem:List<ResponseSubCategories.Data.Home>):
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