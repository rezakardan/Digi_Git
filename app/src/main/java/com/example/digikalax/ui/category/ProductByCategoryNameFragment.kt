package com.example.digikalax.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digikalax.R
import com.example.digikalax.databinding.FragmentProductByCategoryNameBinding

import com.example.digikalax.ui.category.adapters.ProductByCategoryNameAdapter
import com.example.digikalax.ui.product_detail.adapters.SimilarProductsAdapter
import com.example.digikalax.utils.extentions.singleObserve
import com.example.digikalax.utils.network.NetworkRequest
import com.example.digikalax.viewmodel.categories.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductByCategoryNameFragment : Fragment() {

lateinit var binding:FragmentProductByCategoryNameBinding



private val viewModel by viewModels<CategoryViewModel>()



    @Inject
    lateinit var similarProductsAdapter: SimilarProductsAdapter


    @Inject
    lateinit var productByCategoryNameAdapter: ProductByCategoryNameAdapter

private val args:ProductByCategoryNameFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProductByCategoryNameBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        similarProductsAdapter.setOnItemClickListener {



            val directions=ProductByCategoryNameFragmentDirections.actionProductByCategoryNameFragmentToProductDetailFragment(it)

            findNavController().navigate(directions)

            findNavController().popBackStack()





        }








        binding.back.setOnClickListener {



            findNavController().popBackStack()
        }


      args.let {

           viewModel.getProductByCategory(it.categoryName)



       }






        productByCategoryNameAdapter.setOnItemClickListener {


            val directions=ProductByCategoryNameFragmentDirections.actionProductByCategoryNameFragmentToProductDetailFragment(it)

            findNavController().navigate(directions)


        }





        loadProductByCategoryName()


    }


    private fun loadProductByCategoryName() {
        viewModel.getProductByCategoryLiveData.observe(viewLifecycleOwner) { response ->


            when (response) {

                is NetworkRequest.Loading -> {


                    binding.progressBar.visibility=View.VISIBLE
                    binding.subCategoryRecy.visibility=View.GONE

                }


                is NetworkRequest.Success -> {

                    binding.progressBar.visibility=View.GONE

                    binding.subCategoryRecy.visibility=View.VISIBLE
                    response.data?.data?.let { data ->

                        productByCategoryNameAdapter.setData(data)

                        binding.subCategoryRecy.adapter = productByCategoryNameAdapter

                        binding.subCategoryRecy.layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                        )


                    }
                }


                is NetworkRequest.Error -> {

                    Toast.makeText(context, response.error.toString(), Toast.LENGTH_SHORT).show()
                }


            }


        }
    }


}