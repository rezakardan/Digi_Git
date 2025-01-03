package com.example.digikalax.ui.category.adapters

import android.graphics.Paint
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.data.models.categories.ResponseGetProductByCategory
import com.example.digikalax.data.models.categories.ResponseGetProductBySubCategory
import com.example.digikalax.databinding.ItemProductByCategoryBinding
import javax.inject.Inject

class ProductByCategoryNameAdapter  @Inject constructor() :
    RecyclerView.Adapter<ProductByCategoryNameAdapter.ProductByCategoryNameViewHolder>() {


    lateinit var binding: ItemProductByCategoryBinding


    private val decimalFormatter by lazy { DecimalFormat("##,###.##") }


    var beautyItems = emptyList<ResponseGetProductByCategory.Data>()

    inner class ProductByCategoryNameViewHolder(items: View) : RecyclerView.ViewHolder(items) {


        fun onBind(items: ResponseGetProductByCategory.Data) {


            binding.img.load(items.image)

            binding.name.text = items.name

            binding.txtStar.text = items.star.toString()


            if (items.discountPercent > 0) {



                binding.darsad.text="${items.discountPercent}%"



                val discount = "${((items.price.toLong()) * (items.discountPercent.toLong())) / 100}"




                val finalPrice=((items.price.toLong())-(discount.toLong()))





                binding.finalPrice.text =  "${decimalFormatter.format(finalPrice)} تومان "

            } else {

                binding.darsad.text = "0%"

            }

            binding.PriceBeforeDiscount.apply {

                text = "${decimalFormatter.format(items.price!!.toInt())} تومان "

                paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            }

            binding.anbar.text = items.seller



            binding.root.setOnClickListener {

                onItemClickListener?.let {

                    it(items.id)


                }






            }




        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductByCategoryNameViewHolder {
        binding =
            ItemProductByCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductByCategoryNameViewHolder(binding.root)
    }

    override fun getItemCount() = beautyItems.size

    override fun onBindViewHolder(holder: ProductByCategoryNameViewHolder, position: Int) {
        return holder.onBind(beautyItems[position])
    }

    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()





    private var onItemClickListener:((String)->Unit)?=null


    fun setOnItemClickListener(listener:(String)->Unit){


        onItemClickListener=listener



    }





    fun setData(data: List<ResponseGetProductByCategory.Data>) {

        val diffUtils = DiffUtilssssssssssssssssssssssss(beautyItems, data)
        val diff = DiffUtil.calculateDiff(diffUtils)
        beautyItems = data
        diff.dispatchUpdatesTo(this)


    }


}


class DiffUtilssssssssssssssssssssssss(
    val oldItem: List<ResponseGetProductByCategory.Data>,
    val newItem: List<ResponseGetProductByCategory.Data>
) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldItem.size
    }

    override fun getNewListSize(): Int {
        return newItem.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem[oldItemPosition] === newItem[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItem[oldItemPosition] === newItem[newItemPosition]
    }


}