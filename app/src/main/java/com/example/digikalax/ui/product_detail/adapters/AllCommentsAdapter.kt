package com.example.digikalax.ui.product_detail.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.digikalax.R
import com.example.digikalax.data.models.comment.ResponseGetAllComments
import com.example.digikalax.databinding.ItemNazaratBinding
import com.example.digikalax.utils.extentions.DigitHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AllCommentsAdapter @Inject constructor(@ApplicationContext val context: Context) :
    PagingDataAdapter<ResponseGetAllComments.Data, AllCommentsAdapter.AllCommentsViewHolder>(
        differCallBack
    ) {


    lateinit var binding: ItemNazaratBinding

    inner class AllCommentsViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun onBind(item: ResponseGetAllComments.Data) {


            binding.imageView5.load(R.drawable.like)
            binding.imageView5.setColorFilter(
                ContextCompat.getColor(
                    context,
                    R.color.green
                )
            )

            binding.txt11.text = context.getString(R.string.good_comment)

            binding.txt11.setTextColor(ContextCompat.getColor(context, R.color.green))



            when (item.star) {

                in Int.MIN_VALUE..2 -> {


                    val dislike = (R.drawable.like).rotateRight(180)

                    binding.imageView5.load(dislike)
                    binding.imageView5.setColorFilter(
                        ContextCompat.getColor(
                            context,
                            R.color.chineseYellow
                        )
                    )
                    binding.txt11.text = context.getString(R.string.bad_comment)

                    binding.txt11.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.chineseYellow
                        )
                    )
                }


                in 2..3 -> {
                    binding.imageView5.load(R.drawable.like)

                    binding.imageView5.setColorFilter(
                        ContextCompat.getColor(
                            context,
                            R.color.darkGunMetal
                        )
                    )



                    binding.txt11.text = context.getString(R.string.so_so_comment)

                    binding.txt11.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.darkGunMetal
                        )
                    )

                }

                in 3..Int.MAX_VALUE -> {
                    binding.imageView5.load(R.drawable.like)
                    binding.imageView5.setColorFilter(
                        ContextCompat.getColor(
                            context,
                            R.color.green
                        )
                    )

                    binding.txt11.text = context.getString(R.string.good_comment)

                    binding.txt11.setTextColor(ContextCompat.getColor(context, R.color.green))

                }

            }







            binding.txtDesc.text = item.description

            val splitTime = item.updatedAt.split("T")[0].split("-")

            val year = splitTime[0].toInt()
            val month = splitTime[1].toInt()
            val day = splitTime[2].toInt()


            binding.txtBottom1.text = DigitHelper.gregorianToJalali(year, month, day)

            binding.txtBottom2.text = item.userName


        }
    }

    override fun onBindViewHolder(holder: AllCommentsViewHolder, position: Int) {

        holder.onBind(getItem(position)!!)

        holder.setIsRecyclable(false)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCommentsViewHolder {
        binding = ItemNazaratBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllCommentsViewHolder(binding.root)

    }


    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {

        onItemClickListener = listener


    }


    companion object {


        private val differCallBack = object : DiffUtil.ItemCallback<ResponseGetAllComments.Data>() {
            override fun areItemsTheSame(
                oldItem: ResponseGetAllComments.Data,
                newItem: ResponseGetAllComments.Data
            ): Boolean {
                return oldItem.id == newItem.id


            }

            override fun areContentsTheSame(
                oldItem: ResponseGetAllComments.Data,
                newItem: ResponseGetAllComments.Data
            ): Boolean {
                return oldItem == newItem
            }


        }
    }


}