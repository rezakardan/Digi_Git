package com.example.digikalax.ui.product_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.digikalax.R
import com.example.digikalax.databinding.FragmentTechnicalProductBinding
import com.example.digikalax.viewmodel.product_detail.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject

@AndroidEntryPoint
class TechnicalProductFragment : Fragment() {

lateinit var binding: FragmentTechnicalProductBinding


private val args:TechnicalProductFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTechnicalProductBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

args.let {





        val jsonObject = JSONObject(it.jsonstring.toString())

    val keys=jsonObject.keys()

    while (keys.hasNext()){

        val key=keys.next()

        val value=jsonObject.get(key)



        binding.txtDescription.text=key

        binding.Description.text=value.toString()




    }



}

    }


}