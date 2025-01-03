package com.example.digikalax.ui.product_detail.adapters

import android.graphics.Paint
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.R
import com.example.digikalax.data.models.product_detail.ResponseGetSimilarProducts
import com.example.digikalax.databinding.ItemfavoriteproductsBinding
import javax.inject.Inject

class SimilarProductsAdapter @Inject constructor() :
    RecyclerView.Adapter<SimilarProductsAdapter.SimilarProductsViewHolder>() {


    lateinit var binding: ItemfavoriteproductsBinding

    var favoriteItems = emptyList<ResponseGetSimilarProducts.Data>()


    private val decimalFormatter by lazy { DecimalFormat("##,###.##") }

    inner class SimilarProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(items: ResponseGetSimilarProducts.Data) {


            binding.imgFavorite.load(items.image)

            binding.txtTitleFavorite.text = items.name

            binding.imgsellFavorite.load(R.drawable.in_stock)


            binding.txtSellFavorite.text = items.seller


            binding.txtDiscountFav.text = "${items.discountPercent.toString()} %"


            val discount = "${((items.price) * (items.discountPercent)) / 100}"



            val priceAfterDiscount=((items.price.toLong())-(discount.toLong()))



            binding.txtPriceDiscFav.text = "${decimalFormatter.format(priceAfterDiscount)} تومان "

            binding.txtPriceFav.apply {

                text = "${decimalFormatter.format(items.price.toInt())} تومان "

                paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


            }

binding.root.setOnClickListener {






            onItemClickListener?.let {


                it(items.id)

            }


        }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarProductsViewHolder {
        binding =
            ItemfavoriteproductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimilarProductsViewHolder(binding.root)
    }

    override fun getItemCount() = favoriteItems.size

    override fun onBindViewHolder(holder: SimilarProductsViewHolder, position: Int) {
        return holder.onBind(favoriteItems[position])
    }


    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()


    fun setData(data: List<ResponseGetSimilarProducts.Data>) {

        val diffUtils = DiffUtilsssssssss(favoriteItems, data)
        val diff = DiffUtil.calculateDiff(diffUtils)
        favoriteItems = data
        diff.dispatchUpdatesTo(this)


    }




    private var onItemClickListener:((String)->Unit)?=null

    fun setOnItemClickListener(listener:(String)->Unit){

        onItemClickListener=listener
    }


    class DiffUtilsssssssss(
        private val oldItem: List<ResponseGetSimilarProducts.Data>,
        private val newItem: List<ResponseGetSimilarProducts.Data>
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







}