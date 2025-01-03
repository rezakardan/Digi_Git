package com.example.digikalax.ui.home.adapters

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
import com.example.digikalax.data.models.home.ResponsSuperMarketProducts
import com.example.digikalax.data.models.home.ResponseMostDiscountedProducts
import javax.inject.Inject

class MostDiscountedAdapter@Inject constructor(): RecyclerView.Adapter<MostDiscountedAdapter.MostDiscountedViewHolder>() {


    lateinit var binding: ItemAmazingBinding


    val decimalFormatter by lazy { DecimalFormat("##,###.##") }




    var mostDiscountItems= emptyList<ResponseMostDiscountedProducts.Data>()

    inner class MostDiscountedViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseMostDiscountedProducts.Data){






            binding.img.load(items.image)

            binding.title.text=items.name

            binding.imgsell.load(R.drawable.in_stock)

            binding.txtSell.text=items.seller





            if (items.discountPercent!=null ||  items.discountPercent>0) {
                binding.txtPercent.text = "${items.discountPercent} %"
            }else{


                binding.txtPercent.text ="0"

            }



            val discount="${((items.price)*(items.discountPercent))/100}"


            val priceAfterDiscount=((items.price.toLong())-(discount.toLong()))





            binding.discountprice.text="${decimalFormatter.format(priceAfterDiscount)} تومان "

            binding.Price.apply {

                text="${decimalFormatter.format(items.price.toInt())} تومان"

                paintFlags=this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            }
            binding.root.setOnClickListener {


                onItemClickListener?.let {
                    it(items.id)
                }
            }


        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostDiscountedViewHolder {
        binding= ItemAmazingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MostDiscountedViewHolder(binding.root)
    }

    override fun getItemCount()=mostDiscountItems.size

    override fun onBindViewHolder(holder: MostDiscountedViewHolder, position: Int) {
        return holder.onBind(mostDiscountItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()

    private var onItemClickListener:((String)->Unit)?=null

    fun setOnItemClickListener(listener:(String)->Unit){


        onItemClickListener=listener

    }

    fun setData(data:List<ResponseMostDiscountedProducts.Data>){

        val diffUtils=DiffUtilssssssssss(mostDiscountItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        mostDiscountItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilssssssssss(val oldItem:List<ResponseMostDiscountedProducts.Data>, val newItem:List<ResponseMostDiscountedProducts.Data>):
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