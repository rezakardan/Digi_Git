package com.example.digikalax.ui.addresses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.digikalax.data.models.addresses.ResponseGetUserAddress
import com.example.digikalax.databinding.ItemAddressesBinding
import javax.inject.Inject

class AddressAdapter@Inject constructor():RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {

    lateinit var binding: ItemAddressesBinding


    private var addressItem= emptyList<ResponseGetUserAddress.Data>()

    inner class AddressViewHolder(item:View):RecyclerView.ViewHolder(item){


        fun onBind(item:ResponseGetUserAddress.Data){

            binding.address.text=item.address.toString()

            binding.user.text=item.name.toString()

            binding.postalCode.text=item.postalCode.toString()

            binding.phone.text=item.phone.toString()



binding.root.setOnClickListener {



    onItemClickListener?.let {


        it(item)
    }


}



        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        binding= ItemAddressesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddressViewHolder(binding.root)
    }

    override fun getItemId(position: Int)=position.toLong()

    override fun getItemViewType(position: Int)=position

    override fun getItemCount()=addressItem.size

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {




        holder.onBind(addressItem[position])

    }






   private var onItemClickListener:((ResponseGetUserAddress.Data)->Unit)?=null



    fun setOnItemClickListener(listener:(ResponseGetUserAddress.Data)->Unit){


        onItemClickListener=listener



    }



    fun setData(data:List<ResponseGetUserAddress.Data>){


        val diffUtils=AddressDiffUtils(addressItem,data)

        val diff=DiffUtil.calculateDiff(diffUtils)

        addressItem=data

        diff.dispatchUpdatesTo(this)


    }



    class AddressDiffUtils(val oldItem:List<ResponseGetUserAddress.Data>,val newItem:List<ResponseGetUserAddress.Data>):DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
return newItem.size       }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
return oldItem[oldItemPosition]  ===newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition]  ===newItem[newItemPosition]
        }


    }


}