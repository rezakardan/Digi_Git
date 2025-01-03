package com.example.digikalax.ui.home.adapters

import android.content.Context
import android.graphics.Paint
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.R
import com.example.digikalax.databinding.ItemAmazingBinding
import com.example.digikalax.data.models.home.ResponseGetAmazingProducts
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AmazingAdapter@Inject constructor(@ApplicationContext val context: Context):RecyclerView.Adapter<AmazingAdapter.AmazingViewHolder>() {


    lateinit var binding: ItemAmazingBinding


    val decimalFormatter by lazy { DecimalFormat("##,###.##") }




    var amazingItems= emptyList<ResponseGetAmazingProducts.Data>()

    inner class AmazingViewHolder(items:View):RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseGetAmazingProducts.Data){





            binding.name.text= "${context.getString(R.string.amazing_for_app)}"
            binding.img.load(items.image)

            binding.title.text=items.name

            binding.imgsell.load(R.drawable.in_stock)

            binding.txtSell.text=items.seller



            if (items.discountPercent.toString().isNotEmpty() || items.discountPercent>0) {
                binding.txtPercent.text = "${items.discountPercent} %"

            }else{

                binding.txtPercent.text ="0"
            }


            val discount="${((items.price)*(items.discountPercent))/100}"



            val priceAfterDiscounted=((items.price.toLong())-(discount.toLong()))




            binding.discountprice.text="${decimalFormatter.format(priceAfterDiscounted.toLong())} تومان "

           binding.Price.apply {

               text="${decimalFormatter.format(items.price.toInt())} تومان"

               paintFlags=this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

           }



            binding.root.setOnClickListener {

                onItemClickListener?.let {

                    it(items)


                }



            }


        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmazingViewHolder {
binding= ItemAmazingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return AmazingViewHolder(binding.root)
    }

    override fun getItemCount()=amazingItems.size

    override fun onBindViewHolder(holder: AmazingViewHolder, position: Int) {
return holder.onBind(amazingItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()



    private var onItemClickListener:((ResponseGetAmazingProducts.Data)->Unit)?=null

    fun setOnItemClickListener(listener:(ResponseGetAmazingProducts.Data)->Unit){

        onItemClickListener=listener
    }





    fun setData(data:List<ResponseGetAmazingProducts.Data>){

        val diffUtils=DiffUtilssssss(amazingItems,data)
        val diff=DiffUtil.calculateDiff(diffUtils)
        amazingItems=data
diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilssssss(val oldItem:List<ResponseGetAmazingProducts.Data>, val newItem:List<ResponseGetAmazingProducts.Data>):DiffUtil.Callback(){
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