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

class SportAdapter @Inject constructor(): RecyclerView.Adapter<SportAdapter.SportViewHolder>() {


    lateinit var binding: ItemcategoriesBinding






    var sportItems= emptyList<ResponseSubCategories.Data.Sport>()

    inner class SportViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseSubCategories.Data.Sport){


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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        binding= ItemcategoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SportViewHolder(binding.root)
    }

    override fun getItemCount()=sportItems.size

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        return holder.onBind(sportItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()



    fun setData(data:List<ResponseSubCategories.Data.Sport>){

        val diffUtils= DiffUtilssssssssssssssssss(sportItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        sportItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilssssssssssssssssss(val oldItem:List<ResponseSubCategories.Data.Sport>, val newItem:List<ResponseSubCategories.Data.Sport>):
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