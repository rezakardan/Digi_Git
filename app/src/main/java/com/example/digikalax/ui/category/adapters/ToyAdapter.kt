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

class ToyAdapter@Inject constructor(): RecyclerView.Adapter<ToyAdapter.ToyViewHolder>() {


    lateinit var binding: ItemcategoriesBinding






    var toyItems= emptyList<ResponseSubCategories.Data.Toy>()

    inner class ToyViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseSubCategories.Data.Toy){


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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToyViewHolder {
        binding= ItemcategoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ToyViewHolder(binding.root)
    }

    override fun getItemCount()=toyItems.size

    override fun onBindViewHolder(holder: ToyViewHolder, position: Int) {
        return holder.onBind(toyItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()



    fun setData(data:List<ResponseSubCategories.Data.Toy>){

        val diffUtils= DiffUtilsssssssssssssssssssss(toyItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        toyItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilsssssssssssssssssssss(val oldItem:List<ResponseSubCategories.Data.Toy>, val newItem:List<ResponseSubCategories.Data.Toy>):
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