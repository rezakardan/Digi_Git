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

class BeautyAdapter @Inject constructor(): RecyclerView.Adapter<BeautyAdapter.BeautyViewHolder>() {


    lateinit var binding: ItemcategoriesBinding






    var beautyItems= emptyList<ResponseSubCategories.Data.Beauty>()

    inner class BeautyViewHolder(items: View): RecyclerView.ViewHolder(items) {


        fun onBind(items: ResponseSubCategories.Data.Beauty) {


            binding.imgCategory.load(items.image)

            binding.titleCategory.text = items.name

            binding.numberCategory.text = "  کالا ${items.count.toString()} + "



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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeautyViewHolder {
        binding= ItemcategoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BeautyViewHolder(binding.root)
    }

    override fun getItemCount()=beautyItems.size

    override fun onBindViewHolder(holder: BeautyViewHolder, position: Int) {
        return holder.onBind(beautyItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()




    fun setData(data:List<ResponseSubCategories.Data.Beauty>){

        val diffUtils= DiffUtilsssssssssssssssssss(beautyItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        beautyItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilsssssssssssssssssss(val oldItem:List<ResponseSubCategories.Data.Beauty>, val newItem:List<ResponseSubCategories.Data.Beauty>):
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