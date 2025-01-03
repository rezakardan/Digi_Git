package com.example.digikalax.ui.product_detail.adapters

import android.content.Context
import android.graphics.Paint
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.data.db.favorite.FavoriteEntity
import com.example.digikalax.data.models.product_detail.ResponseGetProductById
import com.example.digikalax.databinding.ItemfavoritelistBinding
import com.example.digikalax.ui.home.adapters.FavoriteAdapter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MyFavoriteAdapter @Inject constructor(@ApplicationContext val context: Context) :
    RecyclerView.Adapter<MyFavoriteAdapter.MyFavoriteViewHolder>() {

    lateinit var binding: ItemfavoritelistBinding


    private var itemsShowCase = emptyList<FavoriteEntity>()


    private val decimalFormatter by lazy { DecimalFormat("##,###.##") }

    inner class MyFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun onBind(item: FavoriteEntity) {


            binding.imgFavorite.load(item.image)


            binding.txtTitleFavorite.text = item.name

            binding.txtStarFavorite.text = item.star.toString()



            binding.txtPriceFav.apply {

                text = "${decimalFormatter.format(item.price!!.toInt())} تومان "

                paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


            }
            binding.txtDiscountFav.text = "${item.discountPercent.toString()} %"


            if (item.discountPercent!!.toInt()>0){


                val discount = "${((item.price!!.toLong()) * (item.discountPercent!!.toLong())) / 100}"




                val finalPrice=((item.price!!.toLong())-(discount.toLong()))




                binding.txtPriceDiscFav.text = "${decimalFormatter.format(finalPrice)} تومان "

            }else{

                binding.txtPriceDiscFav.text = "0%"


            }


            binding.root.setOnClickListener {

                onItemClickListener?.let {

it(item,"navigate")


                }


            }


            binding.removeItem.setOnClickListener {


                onItemClickListener?.let {

                    it(item,"delete")


                }



            }




        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFavoriteViewHolder {
        binding =
            ItemfavoritelistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyFavoriteViewHolder(binding.root)
    }

    override fun getItemCount() = itemsShowCase.size
    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()
    override fun onBindViewHolder(holder: MyFavoriteViewHolder, position: Int) {


        holder.onBind(itemsShowCase[position])


    }

    private var onItemClickListener: ((FavoriteEntity,String) -> Unit)? = null

    fun setOnItemClickListener(listener: (FavoriteEntity,String)-> Unit) {

        onItemClickListener = listener


    }

    fun setData(data: List<FavoriteEntity>) {

        val diffUtiles = DiffUtilssss(itemsShowCase, data)
        val diff = DiffUtil.calculateDiff(diffUtiles)

        itemsShowCase = data

        diff.dispatchUpdatesTo(this)


    }


}


class DiffUtilssss(
    private val oldItem: List<FavoriteEntity>,
    private val newItem: List<FavoriteEntity>
) : DiffUtil.Callback() {
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