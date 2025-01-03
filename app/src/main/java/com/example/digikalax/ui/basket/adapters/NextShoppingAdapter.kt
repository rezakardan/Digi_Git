package com.example.digikalax.ui.basket.adapters

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.data.db.basket.CartEntity
import com.example.digikalax.data.db.basket.CartStatus
import com.example.digikalax.databinding.ItemNextShoppingBasketBinding
import com.example.digikalax.databinding.ItemShoppingBasketBinding
import javax.inject.Inject

class NextShoppingAdapter @Inject constructor() :
    RecyclerView.Adapter<NextShoppingAdapter.NextShoppingCartViewHolder>() {


    lateinit var binding: ItemNextShoppingBasketBinding


    val decimalFormatter by lazy { DecimalFormat("##,###.##") }


    var nextShoppingItems = emptyList<CartEntity>()

    inner class NextShoppingCartViewHolder(items: View) : RecyclerView.ViewHolder(items) {


        fun onBind(items: CartEntity) {

            binding.txtNameInforms.text = items.name

            binding.txtSeller.text = items.seller.toString()

            binding.imgInforms.load(items.image)




            binding.tedadeProduct.text = items.count.toString()






if (items.discountPercent!=null){

    val discount= ((items.price)?.times((items.discountPercent!!.toInt())))?.div(100)

    binding.discountPricesss.text=     "   تخفیف :   ${decimalFormatter.format(discount!!.toLong())}تومان  "


    val finalPrice=((items.price)?.minus((discount.toLong())))
    binding.price.text =
        "   قیمت :  ${decimalFormatter.format(finalPrice!!.toLong())} تومان "

}else{

    binding.discountPricesss.visibility=View.GONE


    binding.price.text =
        "  تومان  ${decimalFormatter.format((items.price))}"


}



















            binding.txtDeleteFromNextShopping.setOnClickListener {

                onItemClickListener?.let {
                    it(items,"deleteFromNextCart")
                }


            }



            binding.imageChangeStatus.setOnClickListener {


                onItemClickListener?.let {


                    it(items,"changeStatus")




                }



            }



            }





        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextShoppingCartViewHolder {
        binding =
            ItemNextShoppingBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NextShoppingCartViewHolder(binding.root)
    }

    override fun getItemCount()=nextShoppingItems.size

    override fun onBindViewHolder(holder: NextShoppingCartViewHolder, position: Int) {
        return holder.onBind(nextShoppingItems[position])    }





    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position



    private var onItemClickListener: ((CartEntity,String) -> Unit)? = null

    fun setOnItemClickListener(listener: (CartEntity,String) -> Unit) {
        onItemClickListener = listener
    }


    fun setData(data: List<CartEntity>) {

        val diffUtils = DiffUtilsssssssssssssssssssss(nextShoppingItems, data)
        val diff = DiffUtil.calculateDiff(diffUtils)
        nextShoppingItems = data
        diff.dispatchUpdatesTo(this)


    }


}















class DiffUtilsssssssssssssssssssss(val oldItem: List<CartEntity>, val newItem: List<CartEntity>) :
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