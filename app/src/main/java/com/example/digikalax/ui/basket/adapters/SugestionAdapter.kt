package com.example.digikalax.ui.basket.adapters

import android.graphics.Paint
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.R
import com.example.digikalax.databinding.ItemSugestionBinding
import com.example.digikalax.data.models.basket.ResponseSugestion
import javax.inject.Inject

class SugestionAdapter @Inject constructor(): RecyclerView.Adapter<SugestionAdapter.SugestionViewHolder>() {


    lateinit var binding: ItemSugestionBinding


    val decimalFormatter by lazy { DecimalFormat("##,###.##") }




    var beautyItems= emptyList<ResponseSugestion.Data>()

    inner class SugestionViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponseSugestion.Data){


            binding.img.load(items.image)

            binding.title.text=items.name

            binding.imgsell.load(R.drawable.in_stock)

            binding.txtSell.text=items.seller

            binding.txtPercent.text="${items.discountPercent} %"




            val discount="${((items.price).times((items.discountPercent))).div(100)}"


            val priceAfterDiscount=((items.price.toLong())-(discount.toLong()))


            binding.discountprice.text="${decimalFormatter.format(priceAfterDiscount)} تومان "

            binding.Price.apply {

                text = "${decimalFormatter.format(items.price?.toInt())} تومان"

                paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


            }





            binding.root.setOnClickListener {


                onItemClickListener?.let {


                    it(items,"detail")


                }


            }

            binding.imgAdd.setOnClickListener {

                onItemClickListener?.let {





                    it(items,"add")
                }





            }

        }





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SugestionViewHolder {
        binding= ItemSugestionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SugestionViewHolder(binding.root)
    }

    override fun getItemCount()=beautyItems.size

    override fun onBindViewHolder(holder: SugestionViewHolder, position: Int) {
        return holder.onBind(beautyItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()



    private var onItemClickListener: ((ResponseSugestion.Data,String) -> Unit)? = null

    fun setOnItemClickListener(listener: (ResponseSugestion.Data,String) -> Unit) {
        onItemClickListener = listener
    }




    fun setData(data:List<ResponseSugestion.Data>){

        val diffUtils= DiffUtilsssssssssssssssssss(beautyItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        beautyItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilsssssssssssssssssss(val oldItem:List<ResponseSugestion.Data>, val newItem:List<ResponseSugestion.Data>):
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