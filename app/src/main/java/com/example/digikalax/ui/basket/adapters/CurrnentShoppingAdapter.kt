package com.example.digikalax.ui.basket.adapters

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.data.db.basket.CartEntity
import com.example.digikalax.databinding.ItemShoppingBasketBinding
import javax.inject.Inject

class CurrnentShoppingAdapter @Inject constructor() :
    RecyclerView.Adapter<CurrnentShoppingAdapter.CurrentShoppingViewHolder>() {


    lateinit var binding: ItemShoppingBasketBinding


    val decimalFormatter by lazy { DecimalFormat("##,###.##") }


    var beautyItems = emptyList<CartEntity>()

    inner class CurrentShoppingViewHolder(items: View) : RecyclerView.ViewHolder(items) {


        fun onBind(items: CartEntity) {




            binding.tedadeProduct.text = items.count.toString()

            binding.imgInforms.load(items.image)


            binding.txtNameInforms.text = items.name

            binding.txtSeller.text = items.seller.toString()






            binding.txtCounter.text = items.count.toString()


            if (items.count == 1) {

                binding.trash.visibility = View.VISIBLE

                binding.decrease.visibility = View.GONE


            } else {

                binding.decrease.visibility = View.VISIBLE

                binding.trash.visibility = View.GONE


            }


        //    val price=((items.price)?.times((items.count!!)))



            if (items.discountPercent!!.toLong()>0){


                val discount= ((items.price)?.times((items.discountPercent!!)))?.div(100)

                val priceAfterDiscount=((items.price)?.minus((discount!!)))



                binding.price.text =  "  تومان ${decimalFormatter.format(priceAfterDiscount!!.toLong())}"



            }else{

                val discount= items.price
                val priceAfterDiscount=discount


                binding.price.text =  "  تومان ${decimalFormatter.format(priceAfterDiscount!!.toLong())}"

            }







          //  binding.price.text =  "  تومان ${decimalFormatter.format(price!!.toLong())}"

















            binding.imageIncrease.setOnClickListener {








                onItemClickListener?.let { it(items,"increase") }

            }

            binding.decrease.setOnClickListener {



                onItemClickListener?.let { it(items,"decrease") }


            }




            binding.trash.setOnClickListener {


                onItemClickListener?.let {


                    it(items,"delete")

                }


            }


            binding.txtStorageInNextShopping.setOnClickListener {


                onItemClickListener?.let {

                    it(items,"changeStatus")


                }



            }





        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentShoppingViewHolder {
        binding =
            ItemShoppingBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrentShoppingViewHolder(binding.root)
    }

    override fun getItemCount() = beautyItems.size

    override fun onBindViewHolder(holder: CurrentShoppingViewHolder, position: Int) {
        return holder.onBind(beautyItems[position])
    }

    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()


    private var onItemClickListener: ((CartEntity,String) -> Unit)? = null

    fun setOnItemClickListener(listener: (CartEntity,String) -> Unit) {
        onItemClickListener = listener
    }


    fun setData(data: List<CartEntity>) {

        val diffUtils = DiffUtilssssssssssssssssssss(beautyItems, data)
        val diff = DiffUtil.calculateDiff(diffUtils)
        beautyItems = data
        diff.dispatchUpdatesTo(this)


    }


}


class DiffUtilssssssssssssssssssss(val oldItem: List<CartEntity>, val newItem: List<CartEntity>) :
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