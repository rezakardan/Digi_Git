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
import com.example.digikalax.data.models.home.ResponseGetAmazingProducts
import javax.inject.Inject

class SuperMarketAdapter@Inject constructor(): RecyclerView.Adapter<SuperMarketAdapter.SuperMarketViewHolder>() {


    lateinit var binding: ItemAmazingBinding


    val decimalFormatter by lazy { DecimalFormat("##,###.##") }




    var superMarketItems= emptyList<ResponsSuperMarketProducts.Data>()

    inner class SuperMarketViewHolder(items: View): RecyclerView.ViewHolder(items){


        fun onBind(items: ResponsSuperMarketProducts.Data){





            binding.name.text= "شگفت انگیز اختصاصی اپ "
            binding.img.load(items.image)

            binding.title.text=items.name

            binding.imgsell.load(R.drawable.in_stock)

            binding.txtSell.text=items.seller

            if (items.discountPercent.toString().isNotEmpty() || items.discountPercent>0) {
                binding.txtPercent.text = "${items.discountPercent} %"

            }else{

                binding.txtPercent.text ="0"
            }




            val discount="${((items.price)*(items.discountPercent!!))/100}"




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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperMarketViewHolder {
        binding= ItemAmazingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SuperMarketViewHolder(binding.root)
    }

    override fun getItemCount()=superMarketItems.size

    override fun onBindViewHolder(holder: SuperMarketViewHolder, position: Int) {
        return holder.onBind(superMarketItems[position])    }
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()




    private var onItemClickListener:((ResponsSuperMarketProducts.Data)->Unit)?=null

    fun setOnItemClickListener(listener:(ResponsSuperMarketProducts.Data)->Unit){

        onItemClickListener=listener
    }




    fun setData(data:List<ResponsSuperMarketProducts.Data>){

        val diffUtils=DiffUtilssssssss(superMarketItems,data)
        val diff= DiffUtil.calculateDiff(diffUtils)
        superMarketItems=data
        diff.dispatchUpdatesTo(this)




    }





}



class DiffUtilssssssss(val oldItem:List<ResponsSuperMarketProducts.Data>, val newItem:List<ResponsSuperMarketProducts.Data>):
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