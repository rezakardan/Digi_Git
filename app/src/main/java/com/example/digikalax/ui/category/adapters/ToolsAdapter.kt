package com.example.digikalax.ui.category.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.data.models.categories.ResponseGetProductBySubCategory
import com.example.digikalax.databinding.ItemcategoriesBinding
import com.example.digikalax.data.models.categories.ResponseSubCategories
import javax.inject.Inject

class ToolsAdapter@Inject constructor(): RecyclerView.Adapter<ToolsAdapter.CategoriesViewHolder>() {


    lateinit var binding: ItemcategoriesBinding






    var bannerItems= emptyList<ResponseSubCategories.Data.Tool>()

    inner class CategoriesViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseSubCategories.Data.Tool){


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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        binding= ItemcategoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoriesViewHolder(binding.root)
    }





 private   var onItemClickListener:((String)->Unit)?=null

    fun setOnItemClickListener(listener:(String)->Unit){


        onItemClickListener=listener



    }


    override fun getItemCount()=bannerItems.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        return holder.onBind(bannerItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()



    fun setData(data:List<ResponseSubCategories.Data.Tool>){

        val diffUtils= DiffUtilsssssssssss(bannerItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        bannerItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilsssssssssss(val oldItem:List<ResponseSubCategories.Data.Tool>, val newItem:List<ResponseSubCategories.Data.Tool>):
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