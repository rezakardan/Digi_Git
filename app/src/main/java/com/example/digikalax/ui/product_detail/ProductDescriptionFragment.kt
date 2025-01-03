package com.example.digikalax.ui.product_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.digikalax.R
import com.example.digikalax.databinding.FragmentProductDescriptionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDescriptionFragment : Fragment() {

lateinit var binding:FragmentProductDescriptionBinding




private val args by navArgs<ProductDescriptionFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProductDescriptionBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        args.let {description->



binding.description.text=description.description






        }




    }


}