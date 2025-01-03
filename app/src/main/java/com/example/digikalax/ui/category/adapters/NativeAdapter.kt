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

class NativeAdapter @Inject constructor(): RecyclerView.Adapter<NativeAdapter.NativeViewHolder>() {


    lateinit var binding: ItemcategoriesBinding






    var nativeItems= emptyList<ResponseSubCategories.Data.Native>()

    inner class NativeViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseSubCategories.Data.Native){


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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NativeViewHolder {
        binding= ItemcategoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NativeViewHolder(binding.root)
    }

    override fun getItemCount()=nativeItems.size

    override fun onBindViewHolder(holder: NativeViewHolder, position: Int) {
        return holder.onBind(nativeItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()



    fun setData(data:List<ResponseSubCategories.Data.Native>){

        val diffUtils= DiffUtilssssssssssssssssssss(nativeItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        nativeItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilssssssssssssssssssss(val oldItem:List<ResponseSubCategories.Data.Native>, val newItem:List<ResponseSubCategories.Data.Native>):
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