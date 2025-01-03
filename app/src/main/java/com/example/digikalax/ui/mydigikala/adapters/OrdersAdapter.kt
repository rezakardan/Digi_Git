package com.example.digikalax.ui.mydigikala.adapters

import android.content.Context
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digikalax.data.models.order.ResponseGetUserOrders
import com.example.digikalax.databinding.ItemMyOrdersBinding
import com.example.digikalax.utils.extentions.DigitHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class OrdersAdapter@Inject constructor(@ApplicationContext private val context: Context):RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {



    lateinit var binding: ItemMyOrdersBinding


    private val decimalFormatter by lazy { DecimalFormat("##,###.##") }


    var amazingItems = emptyList<ResponseGetUserOrders.Data>()

    inner class OrdersViewHolder(items: View) : RecyclerView.ViewHolder(items) {


        fun onBind(items: ResponseGetUserOrders.Data) {




            binding.orderCode.text=items.transactionId.toString()


            binding.orderPrice.text="${decimalFormatter.format(items.orderTotalPrice.toLong())} تومان "


            val timeSplit=items.createdAt.split("T")[0]

            val dateSplit=timeSplit.split("-")

            val year=dateSplit[0].toInt()
            val month=dateSplit[1].toInt()
            val day=dateSplit[2].toInt()


binding.orderDate.text= DigitHelper.gregorianToJalali(year,month,day)



binding.orderCode.text=items.transactionId




items.orderProducts.let {


    imagesRecycler(it,binding)



}















        }


    }











    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        binding = ItemMyOrdersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrdersViewHolder(binding.root)
    }

    override fun getItemCount() = amazingItems.size

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        return holder.onBind(amazingItems[position])
    }

    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()


    fun imagesRecycler(data:List<ResponseGetUserOrders.Data.OrderProduct>,binding: ItemMyOrdersBinding){

        val imagesAdapter=ImagesAdapter()

        imagesAdapter.setDataImages(data)

        binding.recyOrderInit.adapter=imagesAdapter

        binding.recyOrderInit.layoutManager=
            LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,true)




    }




    private var onItemClickListener:((String,String)->Unit)?= null

    fun setOnItemClickListener(listener:(String,String)->Unit){


        onItemClickListener=listener


    }


    fun setData(data: List<ResponseGetUserOrders.Data>) {

        val diffUtils = DiffUtilsssssss(amazingItems, data)
        val diff = DiffUtil.calculateDiff(diffUtils)
        amazingItems = data
        diff.dispatchUpdatesTo(this)


    }


}


class DiffUtilsssssss(val oldItem: List<ResponseGetUserOrders.Data>, val newItem: List<ResponseGetUserOrders.Data>) :
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