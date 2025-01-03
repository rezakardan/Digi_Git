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

class BookAdapter @Inject constructor(): RecyclerView.Adapter<BookAdapter.BookViewHolder>() {


    lateinit var binding: ItemcategoriesBinding






    var bookItems= emptyList<ResponseSubCategories.Data.Book>()

    inner class BookViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseSubCategories.Data.Book){


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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        binding= ItemcategoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BookViewHolder(binding.root)
    }

    override fun getItemCount()=bookItems.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        return holder.onBind(bookItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()



    fun setData(data:List<ResponseSubCategories.Data.Book>){

        val diffUtils= DiffUtilsssssssssssssss(bookItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        bookItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilsssssssssssssss(val oldItem:List<ResponseSubCategories.Data.Book>, val newItem:List<ResponseSubCategories.Data.Book>):
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