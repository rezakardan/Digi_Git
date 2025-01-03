package com.example.digikalax.utils.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.digikalax.databinding.LoadMoreBinding

class LoadMoreAdapter(private val retry:()->Unit):LoadStateAdapter<LoadMoreAdapter.ViewHolder>() {



lateinit var binding: LoadMoreBinding







    inner class ViewHolder(item:View):RecyclerView.ViewHolder(item){




        init {
            binding.loadMoreRetry.setOnClickListener { retry.invoke() }
        }

fun onBind(state:LoadState){



    binding.apply {

        loadMoreProgress.isVisible=state is LoadState.Loading

        loadMoreRetry.isVisible=state is LoadState.Error



    }



}


    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {


        holder.onBind(loadState)


    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {

        binding= LoadMoreBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding.root)


    }

}